package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Common.Response.AlarmApiResponse;
import com.bootproj.pmcweb.Common.WebSocket.AlarmWebSocketHandler;
import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Service.AccountService;
import com.bootproj.pmcweb.Service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    private final AccountService accountService;

    @GetMapping("")
    public ResponseEntity getAlarmList(@AuthenticationPrincipal User user){
        Account account = accountService.getUserByEmail(user.getUsername());
        List<AlarmApiResponse> alarms = alarmService.getListByUserId(account.getId());
        return new ResponseEntity(Header.OK(alarms), HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity updateAlarmReadStatus(@PathVariable(value="id") Long id){
        alarmService.updateStatusById(id, AlarmStatus.READ);
        return new ResponseEntity(Header.OK(), HttpStatus.OK);
    }
}
