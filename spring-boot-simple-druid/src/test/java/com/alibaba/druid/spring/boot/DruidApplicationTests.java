package com.alibaba.druid.spring.boot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.spring.boot.service.svcinterface.IUuidService;

@Configuration
@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DruidApplicationTests {  
  
	@Autowired
	protected IUuidService uuidService;
	
	@Test  
    public void uuid() {  
        System.out.println( "uuid: " + uuidService.get());;
    }  
    
  
}  
