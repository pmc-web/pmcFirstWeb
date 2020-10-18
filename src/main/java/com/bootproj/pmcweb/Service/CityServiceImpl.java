package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.City;
import com.bootproj.pmcweb.Mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

//    @Autowired
    @Resource
    private CityMapper cityMapper;

    public List<City> selectCityList() {
        return cityMapper.getCityList();
    }
}
