/** 
 * Copyright (C) 2018 Jeebiz (http://jeebiz.net).
 * All Rights Reserved. 
 */
package net.jeebiz.boot.demo.web.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统默认的重定向地址
 */
@Controller
public class IndexController {

	/**
	 *  登录成功后的默认重定向地址：可重写返回的路径进行业务系统定制
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		return "html/index"; 
	}

}
