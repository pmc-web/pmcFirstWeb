package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Dates;
import org.springframework.stereotype.Repository;

@Repository
public interface DateMapper {
    public Integer createDate(Dates dates);
    public Dates getDate(Long id);
    public Integer updateDate(Dates dates);
    public Integer deleteDate(Long id);
}
