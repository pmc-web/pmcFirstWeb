package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Region;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RegionService {
    public List<String> getRegionDepth1();
    public List<String> getRegionDepth2(String regionDepth1);
    public List<Map> getRegionDepth3(String regionDepth1, String regionDepth2);
    public Optional<Region> getRegionById(Long id);
}
