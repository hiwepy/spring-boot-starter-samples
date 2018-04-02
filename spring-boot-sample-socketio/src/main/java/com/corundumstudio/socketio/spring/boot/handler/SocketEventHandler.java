package com.corundumstudio.socketio.spring.boot.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
@Component
public class SocketEventHandler {

	 //String：EventType类型
    private Map<String,SocketIOClient> clients = new ConcurrentHashMap<String,SocketIOClient>();
 
	// 添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
	// 方便后面发送消息时查找到对应的目标client,
	@OnConnect
	public void onConnect(SocketIOClient client) {
		 System.out.printf("建立连接: Session ID %s", client.getSessionId());
		 System.out.println( client.getHandshakeData().getHttpHeaders());
		 System.out.println(client.getHandshakeData().getUrlParams());
	}

	// 消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
	@OnEvent(value = "onSocketEvent")
	public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
		
		System.out.printf("收到消息-from: %s to:%s\n", data.getFrom(), data.getTo());
		
		clients.put(data.getFrom(),client);
		 
		SocketIOClient ioClient = clients.get(data.getTo());
        System.out.println("clientCache");
        if (ioClient == null) {
            System.out.println("你发送消息的用户不在线");
            return;
        }
        ioClient.sendEvent("onSocketEvent", data);
	}
	 
	// 添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		System.out.printf("关闭连接: Session ID %s", client.getSessionId());
	}
	
}
