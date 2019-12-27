/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.hiwepy.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.github.hiwepy.websocket.handler.ChatWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 允许连接的域,只能以http或https开头
		String[] allowsOrigins = { "http://www.xxx.com" };

		// WebIM WebSocket通道
		registry.addHandler(chatWebSocketHandler(), "/           webSocketIMServer").setAllowedOrigins(allowsOrigins)
				.addInterceptors(myInterceptor());
		registry.addHandler(chatWebSocketHandler(), "/sockjs/w          ebSocketIMServer")
				.setAllowedOrigins(allowsOrigins).addInterceptors(myInterceptor()).withSockJS();
	}

	@Bean
	public ChatWebSocketHandler chatWebSocketHandler() {
		return new ChatWebSocketHandler();
	}

	@Bean
	public HandshakeInterceptor[] myInterceptor() {
		return new HandshakeInterceptor[]{new WebSocketHandshakeInterceptor()};
	}
}