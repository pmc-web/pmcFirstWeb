package com.bootproj.pmcweb.Config;

import com.bootproj.pmcweb.Common.WebSocket.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {
    private final StompHandler stompHandler;
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
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*").withSockJS();
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler); // 인터셉터 설정
    }
}
