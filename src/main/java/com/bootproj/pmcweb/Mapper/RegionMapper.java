package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Region;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RegionMapper {
    public List<String> selectRegionDepth1();
    public List<String> selectRegionDepth2(@Param(value = "regionDepth1") String regionDepth1);
    public List<Map> selectRegionDepth3(@Param(value = "regionDepth1") String regionDepth1, @Param(value = "regionDepth2") String regionDepth2);
    public Optional<Region> selectRegionById(Long id);
}
