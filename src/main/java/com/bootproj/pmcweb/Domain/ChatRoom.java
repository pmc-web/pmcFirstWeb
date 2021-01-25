package com.bootproj.pmcweb.Domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ChatRoom implements Serializable {

    // TODO :  uid 세팅 하는 이유? 찾아보기
    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String studyId;
    private long userCount;

    public static ChatRoom create(String studyId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.studyId = studyId;
        return chatRoom;
    }
}
