package com.corundumstudio.socketio.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.corundumstudio.socketio.SocketIOServer;

@Configuration 				// 配置控制
@EnableScheduling
@EnableAutoConfiguration 	// 启用自动配置
@SpringBootApplication
public class SocketioApplication implements ApplicationRunner, CommandLineRunner {

	@Autowired
	private SocketIOServer server;
	
	public static void main(String[] args) throws Exception {

		SpringApplication.run(SocketioApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

	@Override
	public void run(String... args) throws Exception {
		
		/* 
		 * Socket 监听有两种实现
		 * 方式1：通过注解的方式，该方式使用简便
		 * 方式2：通过实现接口方式， 需要先实现接口，并手动注册到server
		 */
		/*server.addConnectListener(new SocketConnectListener());
		server.addDisconnectListener(new SocketDisconnectListener());
		server.addEventListener("onSocketEvent", MessageInfo.class, new SocketDataListener());
		server.addPingListener(new SocketPingListener());*/
		
	}


}
