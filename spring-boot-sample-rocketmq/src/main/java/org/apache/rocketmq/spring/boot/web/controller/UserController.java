package org.apache.rocketmq.spring.boot.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


@RestController
@RequestMapping("/rest/user")
public class UserController {
	
	@RequestMapping(value = "/getUser",method = RequestMethod.POST)
	public Object getUser(String key,String sign) {
		Map<String, Object> obj = new HashMap<>();
		obj.put("key", key);obj.put("sign", sign);
		System.out.println(obj);
		return obj;
	}
	
	@RequestMapping(value = "/getUser2",method = RequestMethod.GET)
	public Object getUser(@RequestParam Map<String, Object> map) {
		System.out.println(JSON.toJSONString(map));
		return map;
	}
	
}
