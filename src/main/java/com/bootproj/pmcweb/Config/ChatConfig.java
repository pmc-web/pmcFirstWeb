package com.bootproj.pmcweb.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Profile("stomp")
@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    // 클라이언트가 메시지를 구독할 endpoint 정의
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // sub 용 sub topic/public
        registry.enableSimpleBroker("/topic");
        // 메세지 보낼 url send : /app/message
        registry.setApplicationDestinationPrefixes("/app");
    }

    // connection 을 맺을때 cors 허용
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 웹 소켓 연결 url : /chatting
        registry.addEndpoint("/chatting").setAllowedOrigins("*").withSockJS();
    }
}
