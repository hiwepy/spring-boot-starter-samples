package com.corundumstudio.socketio.spring.boot.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class SocketDisconnectListener implements DisconnectListener {

	@Override
	public void onDisconnect(SocketIOClient client) {
		System.out.printf("关闭连接: Session ID %s", client.getSessionId());
	}

}
