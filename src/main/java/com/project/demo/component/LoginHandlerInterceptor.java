package com.project.demo.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登陆检查
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null) {
            //未登录，返回首页
            request.setAttribute("error", "请先登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }else {
            System.out.println(user + "  has login");
            if(user.equals(2)) {
                String requestURI = request.getRequestURI();
                if (isRestrictedForUser(requestURI)) {
                    // 普通用户禁止访问的请求
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Forbidden: Access is denied for regular users");
                    return false;
                }
            }
            return true;
        }
    }
    private boolean isRestrictedForUser(String uri) {
        // 定义普通用户禁止访问的URI路径
        String[] restrictedPaths = {"/project/admin"};

        for (String path : restrictedPaths) {
            if (uri.startsWith(path)) {
                return true; // 如果URI以受限制的路径开始，则禁止访问
            }
        }
        return false; // 不在受限制的路径中，允许访问
    }
}