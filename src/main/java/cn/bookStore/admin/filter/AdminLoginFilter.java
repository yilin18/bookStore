package cn.bookStore.admin.filter;


import cn.bookStore.commons.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        User user = (User) session.getAttribute("admin_login");
        if (user != null) {
            // System.out.println("登陆了,拦截器放行");
            filterChain.doFilter(request, response);
        } else {
            // System.out.println("未登录!");
            if (url.endsWith("login.jsp") || url.endsWith("Login.do")) {
                // System.out.println("前往登陆,放行");
                filterChain.doFilter(request, response);
            } else {
                // System.out.println("未登录,拦截!");
                request.setAttribute("msg", "未登录,禁止访问!");
                request.getRequestDispatcher("/admin/login/login.jsp").forward(request, response);
                // response.sendRedirect(request.getContextPath()+"/admin/login/login.jsp");
            }

        }

    }

    @Override
    public void destroy() {

    }
}
