package com.briup.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {
    //1.定义路径匹配器。
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

//AntPathMatcher类的作用是用于匹配路径的，它提供了两个方法：
//match(String pattern, String path)：用于判断路径是否匹配，返回true或false。
//extractPathWithinPattern(String pattern, String path)：
// 用于提取路径中的变量，返回一个Map<String, String>，key为变量名，value为变量值。


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //2.将Servlet转换为HttpServlet
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //3.获取请求资源路径
        String requestURI = request.getRequestURI();
        //4.定义不需要过滤的路径
        String[] paths = {
                "/user/login",
                "/user/register",
                "/css/**",
                "/js/**",
                "/images/**",
                "/fonts/**",
                "/index.html",
                "/",
                "/index",
                "/toIndex",
                "/toLogin",
                "/toRegister",
                "/error"
                };
        //5.判断请求路径是否需要过滤
        boolean flag = false;
        for (String path : paths) {
            if (PATH_MATCHER.match(path, requestURI)) {
                flag = true;
                break;
            }
        }
        //6.如果请求路径需要过滤
        if (!flag) {
            //7.如果用户已经登录，放行
            if (request.getSession().getAttribute("user") != null) {
                filterChain.doFilter(request, response);
            } else {
                //8.如果用户没有登录，跳转到登录页面
                response.sendRedirect("/toLogin");
            }
        } else {
            //9.如果请求路径不需要过滤，直接放行
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

