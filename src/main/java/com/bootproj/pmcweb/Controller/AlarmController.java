package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    final AlarmService alarmService;

    @GetMapping("/{userId}")
    public ResponseEntity getAlarmList(@PathVariable(value = "userId") Long userId){
        List<Alarm> alarms = alarmService.getListByUserId(userId);
        return new ResponseEntity(Header.OK(alarms), HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity updateAlarmReadStatus(@PathVariable(value="id") Long id){
        alarmService.updateStatusById(id, AlarmStatus.READ);
        return new ResponseEntity(Header.OK(), HttpStatus.OK);
    }
}
