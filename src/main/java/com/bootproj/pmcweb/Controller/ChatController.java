package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Redis.RedisPublisher;
import com.bootproj.pmcweb.Domain.ChatMessage;
import com.bootproj.pmcweb.Domain.ChatRoomRepository;
import com.bootproj.pmcweb.Domain.enumclass.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(ChatMessage chatMessage){
//        return chatMessage;
//    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) throws Exception{
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

/** MessageMapping 을 통해 WebSocket으로 들어오는 메시지 발행을 처리한다
 * 클라이언트에서 /app/chat.sendMessage 로 발행 요청을 하면 Controller 가 해당 메시지를 받아 처리한다 // broker : /topic
 * 메시지가 발행되면 /topic/chat/room/{roomId}로 메시지를 send 한다.  // setApplicationDestinationPrefix : /app
 * 클라이언트에서는 이 주소(/topic/chat/room/{roomId})를 구독하고 있다가 메세지가 전달되면 화면에 출력한다
 * @SendTo("/topic/chat/room/{roodId}라고 같다고 본다
 * */
    @MessageMapping("/chat.sendMessage")
    public void sendMessage (ChatMessage message){ // ??
        log.info("sendMessage :{} ",message.toString());

        if(MessageType.JOIN.equals(message.getType())){
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setContent(message.getSender()+"님이 입장하셨습니다.");
        }

        log.info("sendMessage :{} ",message.toString());
        // webSocket에 발행된 메세지를 redis로 발행한다
        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}
