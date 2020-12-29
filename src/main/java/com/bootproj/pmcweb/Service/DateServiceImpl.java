package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Dates;
import com.bootproj.pmcweb.Mapper.DateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DateServiceImpl implements DateService{

    final private DateMapper dateMapper;

    @Override
    public Long createDate(Dates dates) {
        Integer result = dateMapper.createDate(dates);
        if(result==1) return dates.getId();
        return 0L;
    }

    @Override
    public Dates getDates(Long id) {
        return dateMapper.getDate(id);
    }

    @Override
    public Dates updateDates(Dates dates) {
        dateMapper.updateDate(dates);
        return dates;
    }

    @Override
    public Boolean deleteDates(Long id) {
        Integer result = dateMapper.deleteDate(id);
        if(result==0) return false;
        return true;
    }
}
