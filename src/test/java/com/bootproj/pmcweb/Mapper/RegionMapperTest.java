package com.bootproj.pmcweb.Mapper;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionMapperTest {

    @Autowired
    private RegionMapper regionMapper;

    private String regionDepth1 = "서울특별시";
    private String regionDepth2 = "광진구";

    @Test
    void selectRegionDepth1() {
        List<String> result = regionMapper.selectRegionDepth1();

        Assertions.assertThat(result.size()>0);
    }

    @Test
    void selectRegionDepth2() {
        List<String> result = regionMapper.selectRegionDepth2(regionDepth1);
        Assertions.assertThat(result.size()>0);
    }

    @Test
    void selectRegionDepth3() {
        List<Map> result = regionMapper.selectRegionDepth3(regionDepth1, regionDepth2);
        Assertions.assertThat(result.size()>0);
    }
}