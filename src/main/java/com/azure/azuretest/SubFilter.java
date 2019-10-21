package com.azure.azuretest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
   * 只能是POST请求
 * http://localhost:8080/actuator/shutdown   
 * @author A79125897
 *
 */

@WebFilter(filterName = "suFilter",urlPatterns = "/actuator/shutdown")
public class SubFilter implements Filter {

	private Logger logger=LoggerFactory.getLogger(SubFilter.class);
	 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("suFilter is init+>>>>>");
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter is start+>>>>");
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String requestURI = request.getRequestURI();
        logger.info("requestURI is " + requestURI);
        if(requestURI.contains("shutdown")) {
        	//return;
        }
        //调用该方法后，表示过滤器经过原来的url请求处理方法
        filterChain.doFilter(servletRequest,servletResponse);
    }
 
    @Override
    public void destroy() {
        logger.info("suFilter is destroy+>>>>>");
    }

}
