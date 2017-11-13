package org.apache.rocketmq.spring.boot.setup;

import org.apache.rocketmq.spring.boot.event.RocketmqEvent;
import org.apache.rocketmq.spring.boot.handler.EventHandler;
import org.apache.rocketmq.spring.boot.handler.chain.HandlerChain;
import org.springframework.stereotype.Component;

@Component("smsHandler")
public class RocketmqDataSmsHandler implements EventHandler<RocketmqEvent> {

	@Override
	public void doHandler(RocketmqEvent event, HandlerChain<RocketmqEvent> handlerChain) throws Exception {
		
		System.out.println("==============================================================");
		System.out.println("Rule : /Topic-DC-Input/TagB-Input/** ");
		
		long threadId = Thread.currentThread().getId();
		System.out.println(String.format("Thread Id %s Topic %s Tag %s sms to user ....", threadId, event.getTopic(),
				event.getTag()));
		System.out.println(String.format("Receive New Message:  %s ", event.getMsgBody() ));
		
	}
	 
	
}