/** 
 * Copyright (C) 2018 Jeebiz (http://jeebiz.net).
 * All Rights Reserved. 
 */
package net.jeebiz.boot.demo.setup.config;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class DataSourceConfig {
	
    /*@Bean
     * 
     * https://www.cnblogs.com/jin-zhe/p/8203890.html
    public DataSource dragonHADataSource() throws Exception {
        return new DragonHADatasourceBuilder().build("dragon/dragon-ha-config.xml");
    }*/
    
}