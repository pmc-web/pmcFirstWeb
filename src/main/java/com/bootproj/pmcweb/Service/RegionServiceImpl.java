package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;
import com.bootproj.pmcweb.Mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;


    @Override
    public List<String> getRegionDepth1() {
        return regionMapper.selectRegionDepth1();
    }

    @Override
    public List<String> getRegionDepth2(String regionDepth1) {
        return regionMapper.selectRegionDepth2(regionDepth1);
    }

    @Override
    public List<String> getRegionDepth3(String regionDepth1, String regionDepth2) {
        return regionMapper.selectRegionDepth3(regionDepth1, regionDepth2);
    }
}
