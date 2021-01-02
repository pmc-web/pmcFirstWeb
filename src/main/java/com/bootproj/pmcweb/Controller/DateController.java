package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Domain.Dates;
import com.bootproj.pmcweb.Service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/date")
@RestController
@RequiredArgsConstructor
public class DateController {

    private final DateService dateService;

    // TODO : Q 어떤 로직이 실패했을때는 (요청은 받았으나 결과는 요청한대로 이루어지지않음)  어떤 결과를 리턴해줘야 할까요
    @PostMapping
    public ResponseEntity<Header> createDate(@RequestBody Dates dates){
        Long result = dateService.createDate(dates);
        if(result==0) return new ResponseEntity(Header.OK(), HttpStatus.OK);
        else return new ResponseEntity(Header.Error(null), HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping
    public ResponseEntity<Header> updateDate(@RequestBody Dates dates){
        return new ResponseEntity(Header.OK(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Header> deleteDate(Long id){
        boolean result = dateService.deleteDates(id);
        if(result) return new ResponseEntity(Header.OK(), HttpStatus.OK);
        else return new ResponseEntity(Header.Error(null), HttpStatus.NOT_ACCEPTABLE);
    }
}
