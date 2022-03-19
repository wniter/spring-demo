package com.example.springboot.actuator.config;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @create 2022-03-20 6:11
 */
//    https://www.springcloud.cc/spring-boot.html#production-ready
//    典型的Spring安全配置方式
//保护HTTP端点
//@Configuration
//public class ActuatorSecurity extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
//                .anyRequest().hasRole("ENDPOINT_ADMIN")
//                .and()
//                .httpBasic();
//    }
//
//}