package com.alibaba.druid.spring.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.spring.boot.service.svcinterface.IUuidService;

@RestController
public class HelloWorldController {
	
	@Autowired
	protected IUuidService uuidService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String uuid() {
        return "uuid: " + uuidService.get();
    }
    
}
