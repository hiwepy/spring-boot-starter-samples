/** 
 * Copyright (C) 2018 Jeebiz (http://jeebiz.net).
 * All Rights Reserved. 
 */
package net.jeebiz.boot.demo.setup.config;

import java.util.List;

import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.biz.authz.principal.ShiroPrincipalRepository;
import org.apache.shiro.biz.realm.AuthorizingRealmListener;
import org.apache.shiro.biz.web.filter.authc.TrustableFormAuthenticatingFilter;
import org.apache.shiro.biz.web.filter.authc.listener.LoginListener;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.boot.ShiroBizProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.jeebiz.boot.demo.setup.shiro.LoginAuthorizingRealm;

@Configuration
public class ShiroConfig {

	@Bean  
	public ShiroDialect shiroDialect() {  
	    return new ShiroDialect();  
	}
	
	@Bean
	public Realm defRealm(ShiroPrincipalRepository defRepository,
			@Autowired(required = false) List<AuthorizingRealmListener> realmsListeners, ShiroBizProperties properties) {

		LoginAuthorizingRealm authzRealm = new LoginAuthorizingRealm();
		// 认证账号信息提供实现：认证信息、角色信息、权限信息；业务系统需要自己实现该接口
		//authzRealm.setRepository(defRepository);
		// 凭证匹配器：该对象主要做密码校验
		authzRealm.setCredentialsMatcher(new AllowAllCredentialsMatcher());
		// Realm 执行监听：实现该接口可监听认证失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		authzRealm.setRealmsListeners(realmsListeners);
		// 缓存相关的配置：采用提供的默认配置即可
		authzRealm.setCachingEnabled(properties.isCachingEnabled());
		// 认证缓存配置:无状态情况不缓存认证信息
		authzRealm.setAuthenticationCachingEnabled(properties.isAuthenticationCachingEnabled());
		authzRealm.setAuthenticationCacheName(properties.getAuthenticationCacheName());
		// 授权缓存配置:无状态情况不缓存认证信息
		authzRealm.setAuthorizationCachingEnabled(properties.isAuthorizationCachingEnabled());
		authzRealm.setAuthorizationCacheName(properties.getAuthorizationCacheName());

		return authzRealm;
	}
 
	/**
	 * 默认的登录验证过滤器
	 */
	@Bean("authc")
	public FilterRegistrationBean<TrustableFormAuthenticatingFilter> authenticationFilter(
			@Autowired(required = false) List<LoginListener> loginListeners,
			ShiroBizProperties properties) {
		
		TrustableFormAuthenticatingFilter authcFilter = new TrustableFormAuthenticatingFilter();

		// 是否验证验证码
		authcFilter.setCaptchaEnabled(properties.isEnabled());
		// 登录监听：实现该接口可监听账号登录失败和成功的状态，从而做业务系统自己的事情，比如记录日志
		authcFilter.setLoginListeners(loginListeners);
		
		//authcFilter.setSessionStateless(properties.isSessionStateless());
		
		/*
		 * * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter
		 * chain中，这样导致的结果是，所有URL都会被自定义Filter过滤， 而不是Shiro中配置的一部分URL。下面方式可以解决该问题
		 */

		FilterRegistrationBean<TrustableFormAuthenticatingFilter> registration = new FilterRegistrationBean<TrustableFormAuthenticatingFilter>(
				authcFilter);
		registration.setEnabled(false);
		return registration;
	}
	
}
