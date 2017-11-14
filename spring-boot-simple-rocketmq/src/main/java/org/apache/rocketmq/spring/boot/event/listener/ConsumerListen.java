package org.apache.rocketmq.spring.boot.event.listener;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.boot.RocketmqPushConsumerTemplate;
import org.apache.rocketmq.spring.boot.event.RocketmqEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author:钟志苗
 * @Date: 8:35 2017/5/13 
 * @Description:消费监听器
 */
@Component
public class ConsumerListen {

	@Autowired
	private RocketmqPushConsumerTemplate consumerTemplate;

	@EventListener(condition = "#event.topic=='test'")
	public void testListen(RocketmqEvent event) {
		MQPushConsumer consumer = consumerTemplate.getConsumer();
		try {
			String id = new String(event.getMessageExt().getBody(),"utf-8");
			System.out.println("bl"+ id);
		} catch (Exception e) {
			e.printStackTrace();
			if (event.getMessageExt().getReconsumeTimes() <= 1) {// 重复消费1次
				try {
					consumer.sendMessageBack(event.getMessageExt(), 1, null);
				} catch (RemotingException | MQBrokerException | InterruptedException | MQClientException e1) {
					e1.printStackTrace();
					//消息进行定时重试
				}
			} else {
				System.out.println("消息消费失败，定时重试");
			}
		}
	}

	@EventListener(condition = "#event.topic=='order'")
	public void normalListen(RocketmqEvent event) {
		try {
			System.out.println("顺序消息：" + new String(event.getMessageExt().getBody(),"utf-8"));
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
