package com.bootproj.pmcweb.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    String getCurrentTime();
}
