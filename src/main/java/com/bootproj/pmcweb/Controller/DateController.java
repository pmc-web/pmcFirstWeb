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

    @PostMapping
    public ResponseEntity<Header> createDate(@RequestBody Dates dates) throws Exception{
        try {
            Long result = dateService.createDate(dates);
            if(result!=0) log.info("create Date Success : {}", dates);
            return new ResponseEntity(Header.OK(), HttpStatus.OK);
        }catch (Exception e){
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Header> updateDate(@RequestBody Dates dates)throws Exception{
        try {
            Dates result = dateService.updateDates(dates);
            return new ResponseEntity(Header.OK(result), HttpStatus.OK);
        }catch (Exception e){
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Header> deleteDate(Long id) throws Exception{
        try {
            boolean result = dateService.deleteDates(id);
            return new ResponseEntity(Header.OK(), HttpStatus.OK);
         }catch (Exception e){
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
