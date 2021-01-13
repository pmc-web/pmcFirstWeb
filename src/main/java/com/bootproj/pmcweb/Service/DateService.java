package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Dates;

import java.util.List;

public interface DateService {
    public Long createDate(Dates dates);
    public List<Dates> getDatesList(Long studyId, String year, String month, String day);
    public Dates getDateById(Long id);
    public Dates updateDates(Dates dates);
    public Boolean deleteDates(Long id);
    public List<Dates> getRecentDates(Long id, Integer count);
}
