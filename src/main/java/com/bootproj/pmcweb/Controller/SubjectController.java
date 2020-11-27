package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/subject")
@RestController
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<Header> getSubjectDepth2(@RequestParam(value ="subjectDepth1") String subjectDepth1){
        log.info(" in {}", subjectDepth1);
        List<String> result = subjectService.getSubjectDepth2(subjectDepth1);
        return new ResponseEntity(Header.OK(result), HttpStatus.OK);
    }

}
