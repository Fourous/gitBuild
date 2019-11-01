package com.fourous.gitbuild.base.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author fourous
 * @date: 2019/11/1
 * @description: 登录
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getServletPath().endsWith("user.do")) {
            filterChain.doFilter(request, response);
        } else if (httpRequest.getServletPath().endsWith("login.html")) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = httpRequest.getSession();
            String userName = (String) session.getAttribute("username");
            if (userName == null) {

                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 统一处理错误页面定位
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public static void sendError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/gitBuild/login.html");
    }

}
