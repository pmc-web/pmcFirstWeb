package com.bootproj.pmcweb.Domain;

import com.bootproj.pmcweb.Domain.enumclass.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {
    private MessageType type;
    private String sender;
    private String content;
    private String roomId; // uid
    private Long roomName; // studyId
}
