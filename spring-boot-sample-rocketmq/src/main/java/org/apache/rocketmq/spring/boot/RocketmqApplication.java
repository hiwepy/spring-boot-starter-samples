package org.apache.rocketmq.spring.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration 				// 配置控制
@EnableAsync               	//（异步回调）让@Async注解能够生效,不能加在静态方法上
@EnableScheduling          	// 开启Scheduling 注解
@EnableAutoConfiguration 	// 启用自动配置
@SpringBootApplication
public class RocketmqApplication implements ApplicationRunner, CommandLineRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(RocketmqApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

	@Override
	public void run(String... args) throws Exception {

		 
	}
	
}
