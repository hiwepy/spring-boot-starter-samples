package com.alibaba.druid.spring.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration // 配置控制
@EnableScheduling
@EnableAutoConfiguration // 启用自动配置
@SpringBootApplication
public class DruidApplication implements ApplicationRunner, CommandLineRunner {

/*	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
*/	
	public static void main(String[] args) throws Exception {

		SpringApplication.run(DruidApplication.class, args);
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

	@Override
	public void run(String... args) throws Exception {

	}

}
