package com.ruoyi.common.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 飞书服务端异常告警通知器。
 */
@Slf4j
@Component
public class FeishuExceptionNotifier {

    private static final Duration DEDUPLICATION_INTERVAL = Duration.ofMinutes(1);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final ConcurrentMap<String, Long> notificationTimestamps = new ConcurrentHashMap<>();
    private final HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();

    @Value("${feishu.bot.webhook:}")
    private String webhook;

    /**
     * 方法用途：向飞书发送服务端异常告警，并对相同接口与异常类型进行一分钟去重。
     * 参数：requestMethod 为请求方法；requestUri 为请求路径；clientIp 为客户端 IP；throwable 为异常对象。
     * 返回值：无。
     */
    @Async
    public void notifyServerError(String requestMethod, String requestUri, String clientIp, Throwable throwable) {
        if (webhook == null || webhook.isBlank()) {
            return;
        }
        String exceptionType = throwable.getClass().getSimpleName();
        String deduplicationKey = requestMethod + "|" + requestUri + "|" + exceptionType;
        long currentTime = System.currentTimeMillis();
        Long lastNotificationTime = notificationTimestamps.putIfAbsent(deduplicationKey, currentTime);
        if (lastNotificationTime != null) {
            if (currentTime - lastNotificationTime < DEDUPLICATION_INTERVAL.toMillis()) {
                return;
            }
            notificationTimestamps.replace(deduplicationKey, lastNotificationTime, currentTime);
        }
        notificationTimestamps.entrySet().removeIf(entry -> currentTime - entry.getValue() >= DEDUPLICATION_INTERVAL.toMillis());

        String content = "【ERP 服务端异常】\n"
            + "方法：" + requestMethod + "\n"
            + "路径：" + requestUri + "\n"
            + "IP：" + clientIp + "\n"
            + "异常类型：" + exceptionType + "\n"
            + "时间：" + LocalDateTime.now().format(TIME_FORMATTER);
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(webhook))
                .header("Content-Type", "application/json;charset=UTF-8")
                .timeout(Duration.ofSeconds(5))
                .POST(HttpRequest.BodyPublishers.ofString("{\"msg_type\":\"text\",\"content\":{\"text\":\"" + escapeJson(content) + "\"}}"))
                .build();
            httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (Exception e) {
            log.error("飞书服务端异常告警发送失败", e);
        }
    }

    /**
     * 方法用途：转义飞书 JSON 消息中的特殊字符。
     * 参数：value 为待转义的文本。
     * 返回值：可安全写入 JSON 字符串的文本。
     */
    private String escapeJson(String value) {
        return value.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }
}
