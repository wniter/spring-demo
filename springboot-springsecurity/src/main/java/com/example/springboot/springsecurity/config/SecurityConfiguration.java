package com.example.springboot.springsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义secrity配置
 */
//@Slf4j
//@Configuration
//@EnableWebSecurity//开启Spring Security的功能
////prePostEnabled属性决定Spring Security在接口前注解是否可用@PreAuthorize,@PostAuthorize等注解,设置为true,会拦截加了这些注解的接口
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
///**
// * 基于内存的方式，创建两个用户admin/123456，user/123456
// * */
//        auth.inMemoryAuthentication()
//                .withUser("admin")//用户名
//                .password(passwordEncoder().encode("123456"))//密码
//                .roles("ADMIN");//角色
//        auth.inMemoryAuthentication()
//                .withUser("user")//用户名
//                .password(passwordEncoder().encode("123456"))//密码
//                .roles("USER");//角色
//    }
//
//    /**
//     * 指定加密方式
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//// 使用BCrypt加密密码
//        return new BCryptPasswordEncoder();
//    }

//	@SuppressWarnings("deprecation")
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		return new InMemoryUserDetailsManager(
//				User.withDefaultPasswordEncoder().username("user").password("password")
//						.authorities("ROLE_USER").build(),
//				User.withDefaultPasswordEncoder().username("beans").password("beans")
//						.authorities("ROLE_BEANS").build(),
//				User.withDefaultPasswordEncoder().username("admin").password("admin")
//						.authorities("ROLE_ACTUATOR", "ROLE_USER").build());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http.authorizeRequests()
//				.mvcMatchers("/actuator/beans").hasRole("BEANS")
//				.requestMatchers(EndpointRequest.to("health", "info")).permitAll()
//				.requestMatchers(EndpointRequest.toAnyEndpoint().excluding(MappingsEndpoint.class)).hasRole("ACTUATOR")
//				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//				.antMatchers("/foo").permitAll()
//				.antMatchers("/**").hasRole("USER")
//				.and()
//			.cors()
//				.and()
//			.httpBasic();
//		// @formatter:on
//	}
//
//}
