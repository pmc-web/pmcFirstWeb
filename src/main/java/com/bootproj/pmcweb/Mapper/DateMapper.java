package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Dates;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateMapper {
    public Integer createDate(Dates dates);
    public List<Dates> getDatesList(Long studyId, String year, String month, String day);
    public Dates getDate(Long id);
    public Integer updateDate(Dates dates);
    public Integer deleteDate(Long id);
}
