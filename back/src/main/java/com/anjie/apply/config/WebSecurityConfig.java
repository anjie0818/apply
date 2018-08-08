package com.anjie.apply.config;

import com.anjie.apply.service.CustomerUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{//扩张spring security类需要继承此类
    @Bean
    UserDetailsService customerUserService(){
        return new CustomerUserService();//注册CustomerUserService类
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
         auth.userDetailsService(customerUserService());//添加自定义的认证
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests().
                                anyRequest().authenticated()//添加所有请求认证后才可以访问
                                .and()
                                .formLogin()
                                        .loginPage("/login")
                                        .failureUrl("login?error")
                                        .permitAll()//定制登录行为，登录页面可以任意访问
                                .and()
                                .logout().permitAll();//定制注销行为，注销请求可以任意访问
    }
}
