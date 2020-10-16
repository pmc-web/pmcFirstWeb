package com.bootproj.pmcweb.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMapperTest {

    @Autowired
    TestMapper testMapper;

    @Test
    public void testMapper(){
        testMapper.getCurrentTime();
    }
}
