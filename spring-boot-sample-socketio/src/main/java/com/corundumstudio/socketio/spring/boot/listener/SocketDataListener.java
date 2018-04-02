package com.corundumstudio.socketio.spring.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.spring.boot.handler.MessageInfo;

public class SocketDataListener implements DataListener<MessageInfo> {

	 Logger logger = LoggerFactory.getLogger(getClass());
	
	 @Override
     public void onData(final SocketIOClient client, MessageInfo data, AckRequest ackRequest) {
         // 握手
         if (data.getContent().equals("hello")) {
        	 
             String userid = data.getFrom();
             logger.info(Thread.currentThread().getName() + "web读取到的userid：" + userid);

             // send message back to client with ack callback
             // WITH data
             client.sendEvent("onSocketEvent", new AckCallback<String>(String.class) {
                 @Override
                 public void onSuccess(String result) {
                     logger.info("ack from client: " + client.getSessionId() + " data: " + result);
                 }
             }, 1000);

         } else {
             logger.info("行情接收到了不应该有的web客户端请求1111...");
         }
     }
}
