package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @GetMapping("/search/word")
    public ResponseEntity<Header> getSearchList(@RequestParam("searchData") String word){
        List<Map<String, Object>> result = null;

        return new ResponseEntity(Header.OK(result), HttpStatus.OK);
    }
}
