package net.jeebiz.boot.demo;

import javax.sql.DataSource;

import org.dozer.spring.boot.EnableDozerMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.spring4all.swagger.EnableSwagger2Doc;

import net.jeebiz.boot.autoconfigure.EnableServiceConfiguration;
import net.jeebiz.boot.autoconfigure.EnableWebMvcConfiguration;

// 这里特别注意排除Druid的默认初始化对象
@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
@EnableCaching(proxyTargetClass = true)
@EnableSwagger2Doc
@EnableDozerMapper
@EnableScheduling
@EnableServiceConfiguration
@EnableWebMvcConfiguration
@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	// 其中 dataSource 框架会自动为我们注入
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
	public static void main(String[] args) throws Exception {
		 SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Spring Boot Application（Jeebiz-Demo） Started !");
	}
	
}
