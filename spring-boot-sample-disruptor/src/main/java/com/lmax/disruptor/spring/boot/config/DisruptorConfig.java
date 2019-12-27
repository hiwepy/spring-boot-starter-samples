/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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
package com.lmax.disruptor.spring.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lmax.disruptor.spring.boot.DisruptorTemplate;
import com.lmax.disruptor.spring.boot.event.DisruptorBindEvent;

@Configuration
@EnableScheduling
public class DisruptorConfig {

	@Autowired	
	protected DisruptorTemplate disruptorTemplate;

	@Scheduled(fixedDelay = 1000) // 每1s执行1次
	public void send() throws Exception {
		
		DisruptorBindEvent event = new DisruptorBindEvent(this, "message " + Math.random());
		
		event.setEvent("Event-Output");
		event.setTag("TagA-Output");
		event.setKey("id-" + Math.random());
		
		disruptorTemplate.publishEvent(event);
		
	}
	
	@Scheduled(fixedDelay = 1000) // 每1s执行1次
	public void send2() throws Exception {
		
		DisruptorBindEvent event = new DisruptorBindEvent(this, "message " + Math.random());
		
		event.setEvent("Event-Output");
		event.setTag("TagB-Output");
		event.setKey("id-" + Math.random());
		
		disruptorTemplate.publishEvent(event);
		
	}
	
}
