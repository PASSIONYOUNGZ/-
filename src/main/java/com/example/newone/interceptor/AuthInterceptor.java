package com.example.newone.interceptor;

import com.example.newone.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    // 定义角色与允许方法的映射
    private static final Map<String, String[]> ROLE_PERMISSIONS = new HashMap<>();

    static {
        ROLE_PERMISSIONS.put("管理员", new String[]{"GET", "POST", "PUT", "DELETE"}); // 管理员允许所有操作
        ROLE_PERMISSIONS.put("普通用户", new String[]{"GET"}); // 普通用户只允许 GET 操作
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的JWT令牌
        String token = request.getHeader("Authorization");

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing token");
            return false;
        }

        // 如果有 Bearer 前缀，去掉前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // 解析JWT令牌
            Claims claims = JwtUtils.parseJWT(token);

            log.info("Claims: {}", claims);

            // 获取用户角色信息
            String role = (String) claims.get("role");
            request.setAttribute("role", role);

            // 获取请求方法
            String method = request.getMethod();

            // 验证权限
            if (ROLE_PERMISSIONS.containsKey(role)) {
                String[] allowedMethods = ROLE_PERMISSIONS.get(role);
                for (String allowedMethod : allowedMethods) {
                    if (allowedMethod.equalsIgnoreCase(method)) {
                        log.info("User {} has permission to {}", role, method);
                        return true; // 允许访问
                    }
                }
            }

            // 如果不符合条件，返回403
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Permission denied");
            log.info("User {} does not have permission to {}", role, method);
            return false;

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false;
        }
    }
}
