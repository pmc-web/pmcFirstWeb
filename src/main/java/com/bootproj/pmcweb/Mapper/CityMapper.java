package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    public List<City> getCityList();
}
