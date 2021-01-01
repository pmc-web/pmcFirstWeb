package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Dates;
import com.bootproj.pmcweb.Mapper.DateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Dates> getDatesList(Long studyId, String year, String month, String day) {
        // year, month, day를 문자열로 따로 받아서 조회
        return dateMapper.getDatesList(studyId, year, month, day);
    }

    @Override
    public Dates getDateById(Long id) {
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
