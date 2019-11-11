package com.wondersgroup.servlet;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;
import java.io.IOException;

/**
 * @author chenlin
 * @create 2019-05-30 14:10
 * @description:
 * @version：1.0
 **/
@WebFilter(filterName = "firstFilter", urlPatterns = "/*")
@Slf4j
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("first Filter init");
        log.info("first Filter init ");
    }

    @Override
    public void destroy() {
        log.info("first Filter destroy");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("First Filter doFilter " + request.getRequestURI() + "," + request.getContextPath());
        log.info("First Filter doFilter " + request.getRequestURI() + "," + request.getContextPath());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
