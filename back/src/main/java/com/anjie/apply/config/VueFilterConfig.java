package com.anjie.apply.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import  javax.servlet.Filter ;
import  java.io.IOException ;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.* ;

@Configuration
public class VueFilterConfig  {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new VueFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("MyFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

}
 class VueFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init------vue-----filter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getServletPath();
        String context = request.getContextPath();
        //匹配的路径重写
        if (servletPath.contains("ui")) {
            req.getRequestDispatcher(context+"/").forward(req, res);
        }else{
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy-----vue----filter");
    }
}
