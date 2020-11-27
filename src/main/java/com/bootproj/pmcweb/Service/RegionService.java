package com.bootproj.pmcweb.Service;

import java.util.List;
import java.util.Optional;

public interface RegionService {
    public List<String> getRegionDepth1();
    public List<String> getRegionDepth2(String regionDepth1);
    public List<String> getRegionDepth3(String regionDepth1, String regionDepth2);
}
