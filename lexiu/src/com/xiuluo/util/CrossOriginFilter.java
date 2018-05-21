package com.xiuluo.util;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @comment 跨域过滤器
 */
public class CrossOriginFilter implements Filter {

    private FilterConfig config = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {
        this.config = null;
    }

    /**
     * 
     * @comment 跨域的设置
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 表明它允许"http://xxx"发起跨域请求
        httpResponse.setHeader("Access-Control-Allow-Origin",
                "*");
        // 表明在xxx秒内，不需要再发送预检验请求，可以缓存该结果
        httpResponse.setHeader("Access-Control-Allow-Methods",
        		"GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        // 表明它允许xxx的外域请求
        httpResponse.setHeader("Access-Control-Max-Age",
        		"3600");
        // 表明它允许跨域请求包含xxx头
        httpResponse.setHeader("Access-Control-Allow-Headers",
        		"Content-Type, X-Requested-With, X-PINGOTHER, X-File-Name, Cache-Control");
        chain.doFilter(request, response);
    }

}
