package com.ruoyi.system.service;

import cn.hutool.http.HttpRequest;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.json.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 飞书安全告警通知服务
 */
@Slf4j
@Service
public class FeishuSecurityAlertService {

    @Value("${feishu.bot.webhook:}")
    private String webhook;

    /**
     * 发送验证码失败 IP 封禁告警。
     *
     * @param username 本次请求填写的用户名
     * @param clientIp 触发封禁的客户端 IP
     * @param retryCount 触发封禁的验证码失败次数
     * @param lockTime IP 封禁时长，单位分钟
     * @return 无返回值
     */
    @Async
    public void sendCaptchaIpBlockAlert(String username, String clientIp, Integer retryCount, Integer lockTime) {
        if (StringUtils.isBlank(webhook)) {
            return;
        }
        String content = "【ERP 安全告警】验证码失败次数达到封禁阈值\n"
            + "IP：" + clientIp + "\n"
            + "用户名：" + username + "\n"
            + "失败次数：" + retryCount + "\n"
            + "封禁时长：" + lockTime + "分钟\n"
            + "时间：" + DateUtils.getTime();
        Map<String, Object> message = Map.of(
            "msg_type", "text",
            "content", Map.of("text", content)
        );
        try {
            HttpRequest.post(webhook)
                .header("Content-Type", "application/json")
                .body(JsonUtils.toJsonString(message))
                .timeout(5000)
                .execute();
        } catch (Exception e) {
            log.error("飞书安全告警发送失败", e);
        }
    }
}
