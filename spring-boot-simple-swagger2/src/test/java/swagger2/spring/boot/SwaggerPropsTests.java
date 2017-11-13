/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
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
package swagger2.spring.boot;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import io.swagger2.spring.boot.Swagger2Properties;
import io.swagger2.spring.boot.config.DocketInfo;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = ReadApplication.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class SwaggerPropsTests {  
	
    @Autowired  
    private Swagger2Properties ymlConfig;  
      
    @Test  
    public void testSimpleYmlValue() { 
    	
    	/*
    	- swagger.title=标题
		- swagger.description=描述
		- swagger.version=版本
		- swagger.license=许可证
		- swagger.licenseUrl=许可证URL
		- swagger.termsOfServiceUrl=服务条款URL
		- swagger.contact.name=维护人
		- swagger.contact.url=维护人URL
		- swagger.contact.email=维护人email
		- swagger.base-package=swagger扫描的基础包，默认：全扫描
		- swagger.base-path=需要处理的基础URL规则，默认：
		- swagger.exclude-path=需要排除的URL规则，默认：空
		*/
        
        System.out.println("swagger.title: " + ymlConfig.getTitle());    
        System.out.println("swagger.description: " + ymlConfig.getDescription());
        System.out.println("swagger.version: " + ymlConfig.getVersion());
        System.out.println("swagger.license: " + ymlConfig.getLicense());
        System.out.println("swagger.licenseUrl: " + ymlConfig.getLicenseUrl());
        System.out.println("swagger.termsOfServiceUrl: " + ymlConfig.getTermsOfServiceUrl());
        
        System.out.println("swagger.contact.name: " + ymlConfig.getContact().getName());
        System.out.println("swagger.contact.url: " + ymlConfig.getContact().getUrl());
        System.out.println("swagger.contact.email: " + ymlConfig.getContact().getEmail());
        
        System.out.println("swagger.base-package: " + ymlConfig.getBasePackage());
        System.out.println("swagger.base-path: " + StringUtils.collectionToDelimitedString(ymlConfig.getBasePath(), ","));
        System.out.println("swagger.exclude-path: " + StringUtils.collectionToDelimitedString(ymlConfig.getExcludePath(), ","));
        
    }
    
    @Test  
    public void testMoreYmlValue() { 
    	
    	/*
    	- swagger.docket.<name>.title=标题
		- swagger.docket.<name>.description=描述
		- swagger.docket.<name>.version=版本
		- swagger.docket.<name>.license=许可证
		- swagger.docket.<name>.licenseUrl=许可证URL
		- swagger.docket.<name>.termsOfServiceUrl=服务条款URL
		- swagger.docket.<name>.contact.name=维护人
		- swagger.docket.<name>.contact.url=维护人URL
		- swagger.docket.<name>.contact.email=维护人email
		- swagger.docket.<name>.base-package=swagger扫描的基础包，默认：全扫描
		- swagger.docket.<name>.base-path=需要处理的基础URL规则，默认：/**
		- swagger.docket.<name>.exclude-path=需要排除的URL规则，默认：空
		*/
        
    	for (DocketInfo info : ymlConfig.getDockets()) {
			
    		System.out.println("groupName:" + info.getGroupName());   
    		
    		System.out.println("swagger.docket.<name>.title: " + info.getTitle());    
	        System.out.println("swagger.docket.<name>.description: " + info.getDescription());
	        System.out.println("swagger.docket.<name>.version: " + info.getVersion());
	        System.out.println("swagger.docket.<name>.license: " + info.getLicense());
	        System.out.println("swagger.docket.<name>.licenseUrl: " + info.getLicenseUrl());
	        System.out.println("swagger.docket.<name>.termsOfServiceUrl: " + info.getTermsOfServiceUrl());
	        
	        System.out.println("swagger.docket.<name>.contact.name: " + info.getContact().getName());
	        System.out.println("swagger.docket.<name>.contact.url: " + info.getContact().getUrl());
	        System.out.println("swagger.docket.<name>.contact.email: " + info.getContact().getEmail());
	        
	        System.out.println("swagger.docket.<name>.base-package: " + info.getBasePackage());
	        System.out.println("swagger.docket.<name>.base-path: " + StringUtils.collectionToDelimitedString(info.getBasePath(), ","));
	        System.out.println("swagger.docket.<name>.exclude-path: " + StringUtils.collectionToDelimitedString(info.getExcludePath(), ","));
	        
	        System.out.println("===============================================");
	        
    		
		}
    	
    	
       
        
    } 
    
}  