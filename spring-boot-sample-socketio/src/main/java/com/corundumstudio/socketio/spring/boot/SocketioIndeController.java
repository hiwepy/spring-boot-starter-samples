package com.corundumstudio.socketio.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SocketioIndeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String uuid() {
        return "html/socketio/index";
    }
    
}
