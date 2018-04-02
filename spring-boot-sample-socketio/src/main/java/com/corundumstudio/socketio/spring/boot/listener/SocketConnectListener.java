package com.corundumstudio.socketio.spring.boot.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;

public class SocketConnectListener implements ConnectListener {

	@Override
	public void onConnect(SocketIOClient client) {
		
		 System.out.printf("建立连接: Session ID %s", client.getSessionId());
		
	}

}
