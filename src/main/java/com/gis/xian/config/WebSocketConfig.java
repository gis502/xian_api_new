package com.gis.xian.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 启用WebSocket消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 启用简单的内存消息代理，客户端订阅以 "/topic" 开头的目的地
        registry.enableSimpleBroker("/topic");
        // 设置客户端发送消息的前缀，即 "/app"
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册端点 "/websocket"，客户端将通过此路径建立连接
        registry.addEndpoint("/websocket")
                .setAllowedOriginPatterns("*")  // 允许所有源，支持携带凭证
                .withSockJS();
    }
}