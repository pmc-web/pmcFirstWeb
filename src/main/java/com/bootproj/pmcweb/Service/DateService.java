package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Dates;

public interface DateService {
    public Long createDate(Dates dates);
    public Dates getDates(Long id);
    public Dates updateDates(Dates dates);
    public Boolean deleteDates(Long id);
}
