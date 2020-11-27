package com.bootproj.pmcweb.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionMapper {
    public List<String> selectRegionDepth1();
    public List<String> selectRegionDepth2(@Param(value = "regionDepth1") String regionDepth1);
    public List<String> selectRegionDepth3(@Param(value = "regionDepth1") String regionDepth1, @Param(value = "regionDepth2") String regionDepth2);
}
