package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.WebSocket.JwtTokenProvider;
import com.bootproj.pmcweb.Domain.ChatMessage;
import com.bootproj.pmcweb.Domain.ChatRoomRepository;
import com.bootproj.pmcweb.Service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

/** MessageMapping 을 통해 WebSocket으로 들어오는 메시지 발행을 처리한다
 * 클라이언트에서 /app/chat.sendMessage 로 발행 요청을 하면 Controller 가 해당 메시지를 받아 처리한다 // broker : /topic
 * 메시지가 발행되면 /topic/chat/room/{roomId}로 메시지를 send 한다.  // setApplicationDestinationPrefix : /app
 * 클라이언트에서는 이 주소(/topic/chat/room/{roomId})를 구독하고 있다가 메세지가 전달되면 화면에 출력한다
 * @SendTo("/topic/chat/room/{roodId}라고 같다고 본다
 * */

    /**
     * websocket "/pub/chat.sendMessage"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat.sendMessage")
    public void message(ChatMessage message, @Header("token") String token) {
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
}
