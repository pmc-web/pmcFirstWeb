package com.bootproj.pmcweb.Common.WebSocket;

import com.bootproj.pmcweb.Common.Aspect.LogExecutionTime;
import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Service.AccountService;
import com.bootproj.pmcweb.Service.AlarmService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@NoArgsConstructor
@Log4j2
@Component
public class AlarmWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    AlarmService alarmService;

    @Autowired
    AccountService accountService;

    @Override
    @LogExecutionTime
    // 연결이 성사 되고 나서 해야할 일들.
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("알람 웹소켓 연결");
        super.afterConnectionEstablished(session);
    }

    @Override
    @LogExecutionTime
    // 웹소켓 서버단으로 메세지가 도착했을때 해주어야할 일들을 정의하는 메소드 입니다.
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("서버가 받은 메세지: " + message.getPayload());
        Account account = accountService.getUserByEmail(message.getPayload());
        Integer alarmCount = alarmService.getListByUserId(account.getId()).size();
        session.sendMessage(new TextMessage(Integer.toString(alarmCount)));
        super.handleTextMessage(session, message);
    }

    @Override
    @LogExecutionTime
    // 웹 소켓 연결이 종료되고 나서 서버단에서 실행해야할 일들을 정의해주는 메소드
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("알람 웹소켓 종료");
        super.afterConnectionClosed(session, status);
    }
}
