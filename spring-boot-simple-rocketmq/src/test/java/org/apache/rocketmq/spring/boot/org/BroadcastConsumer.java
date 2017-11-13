package org.apache.rocketmq.spring.boot.org;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * http://rocketmq.apache.org/docs/broadcast-example/
 */
public class BroadcastConsumer {
	
	public static void main(String[] args) throws Exception {
		
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");

		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		// set to broadcast mode
		consumer.setMessageModel(MessageModel.BROADCASTING);

		consumer.subscribe("TopicTest", "TagA || TagC || TagD");

		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		consumer.start();
		System.out.printf("Broadcast Consumer Started.%n");
	}
}
