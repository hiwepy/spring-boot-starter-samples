package org.apache.rocketmq.spring.boot;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.boot.RocketmqProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

  
@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketmqApplicationTests {  
      
	@Autowired
	protected RocketmqProducerTemplate rocketmqTemplate;
      
    @Test  
    public void contextLoads() throws MQClientException, RemotingException, MQBrokerException, InterruptedException {  
       // Destination destination = new ActiveMQQueue("mytest.queue");  
    	Message msg = new Message("TEST", // topic
				"TEST", // tag
				"KKK", // key用于标识业务的唯一性
				"hi,RocketMQ".getBytes()// body 二进制字节数组
		);

        for(int i=0; i<100; i++){  
        	SendResult result = rocketmqTemplate.send(msg);
    		System.out.println(result);
    		
        }
    }  
  
}  
