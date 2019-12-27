package com.github.hiwepy.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 1.@EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。<br/>
 * 2.registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL。<br/>
 * 3.stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。<br/>
 * 4.configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic 
 */
@Configuration
@EnableWebSocketMessageBroker
public class DefaultWebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	 /** 
     * 将"/hello"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点， 
     * 即用户发送请求url="/applicationName/sockets"与STOMP server进行连接。之后再转发到订阅url； 
     * PS：端点的作用——客户端在订阅或发布消息到目的地址前，要连接该端点。 
     * @param stompEndpointRegistry 
     */  
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    	 //在网页上可以通过"/applicationName/stomp"来和服务器的WebSocket连接  
        stompEndpointRegistry.addEndpoint("/stomp").setAllowedOrigins("*").withSockJS();
    }
    
    /** 
     * 配置了一个简单的消息代理，如果不重载，默认情况下回自动配置一个简单的内存消息代理，用来处理以"/topic"为前缀的消息。这里重载configureMessageBroker()方法，  消息代理将会处理前缀为"/topic"和"/queue"的消息。 
     * @param registry 
     */  
    @Override  
    public void configureMessageBroker(MessageBrokerRegistry registry) {  
        
    	//应用程序以/app为前缀，代理目的地以/topic 为前缀  
    	registry.enableSimpleBroker("/topic");  
    	
        registry.setApplicationDestinationPrefixes("/app");  
        registry.setUserDestinationPrefix("/user");
        
        /*
        registry.enableSimpleBroker("/topic", "/user");这句话表示在topic和user这两个域上可以向客户端发消息。
        registry.setUserDestinationPrefix("/user");这句话表示给指定用户发送一对一的主题前缀是"/user"。
        registry.setApplicationDestinationPrefixes("/app");这句话表示客户单向服务器端发送时的主题上面需要加"/app"作为前缀。
        stompEndpointRegistry.addEndpoint("/hello").setAllowedOrigins("*").withSokJS();这个和客户端创建连接时的url有关，其中setAllowedOrigins()方法表示允许连接的域名，withSockJS()方法表示支持以SockJS方式连接服务器。
        */
    }  
    
}
