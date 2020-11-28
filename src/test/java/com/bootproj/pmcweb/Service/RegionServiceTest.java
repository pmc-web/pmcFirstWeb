package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Config.DatabaseConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RegionServiceImpl.class, DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionServiceTest {

    @Autowired
    private RegionServiceImpl regionService;

    String depth1 = "서울특별시";
    String depth2 = "광진구";

    @Test
    void depth1(){
        List<String> result = regionService.getRegionDepth1();
        assertThat(result.size()>0);
    }

    @Test
    void depth2(){
        List<String> result = regionService.getRegionDepth2(depth1);
        assertThat(result.size()>0);
    }

    @Test
    void depth3(){
        List<Map> result = regionService.getRegionDepth3(depth1, depth2);
        assertThat(result.size()>0);
    }
}