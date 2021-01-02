package com.bootproj.pmcweb.Domain;

import com.bootproj.pmcweb.Domain.enumclass.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private MessageType type;
    private String sender;
    private String content;
}
