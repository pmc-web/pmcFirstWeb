package com.bootproj.pmcweb.Domain;

import com.bootproj.pmcweb.Domain.enumclass.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("roomId")
    private String roomId; // uid
    @JsonProperty("roomName")
    private String roomName; // studyId
}
