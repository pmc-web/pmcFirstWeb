package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/region")
@RestController
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<Header> getRegionDepth2(@RequestParam(value = "regionDepth1")String regionDepth1){
        List<String> result = regionService.getRegionDepth2(regionDepth1);
        return new ResponseEntity(Header.OK(result), HttpStatus.OK);
    }

    @GetMapping("/depth")
    public ResponseEntity<Header> getRegionDepth3(@RequestParam(value = "regionDepth1")String regionDepth1,
                                                  @RequestParam(value = "regionDepth2")String regionDepth2){
        List<Map> result = regionService.getRegionDepth3(regionDepth1, regionDepth2);
        return new ResponseEntity(Header.OK(result), HttpStatus.OK);
    }
}
