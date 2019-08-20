package com.corundumstudio.socketio.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.handler.SuccessAuthorizationListener;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.corundumstudio.socketio.spring.boot.listener.SocketExceptionListener;

@Configuration
public class SocketioConfig {

	@Bean
	public ExceptionListener exceptionListener() {
		return new SocketExceptionListener();
	}

	@Bean
	public AuthorizationListener socketAuthzListener() {
		return new SuccessAuthorizationListener();
	}
	
}
