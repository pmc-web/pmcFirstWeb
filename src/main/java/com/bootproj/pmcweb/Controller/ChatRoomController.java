package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.WebSocket.JwtTokenProvider;
import com.bootproj.pmcweb.Domain.ChatInfo;
import com.bootproj.pmcweb.Domain.ChatRoom;
import com.bootproj.pmcweb.Domain.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }

    @GetMapping("/user")
    @ResponseBody
    public ChatInfo getUserInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return ChatInfo.builder().token(jwtTokenProvider.generateToken(name)).build();
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
        chatRooms.stream().forEach(room -> room.setUserCount(chatRoomRepository.getUserCount(room.getRoomId())));
        return chatRooms;
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestBody String studyId) {
        return chatRoomRepository.createChatRoom(studyId);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/room";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}