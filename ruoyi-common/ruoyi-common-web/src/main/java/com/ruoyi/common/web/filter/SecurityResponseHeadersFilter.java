package com.ruoyi.common.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 安全响应头过滤器。
 */
@Component
public class SecurityResponseHeadersFilter extends OncePerRequestFilter {

    /**
     * 方法用途：为每个响应补充浏览器侧安全响应头。
     * 参数：request 为 HTTP 请求；response 为 HTTP 响应；filterChain 为过滤器链。
     * 返回值：无。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("Content-Security-Policy", "frame-ancestors 'none'");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        response.setHeader("Permissions-Policy", "camera=(), microphone=(), geolocation=()");
        filterChain.doFilter(request, response);
    }
}
