package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private StudyService studyService;

    @PostMapping("/search/word")
    public ResponseEntity<Header> getSearchList(@RequestParam(required = false, value="searchData") String word){
        List<Study> result = studyService.getSearchList(word);

        return new ResponseEntity(Header.OK(result), HttpStatus.OK);
    }
}
