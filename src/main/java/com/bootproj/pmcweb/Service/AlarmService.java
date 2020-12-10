package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;

import java.util.List;

public interface AlarmService {
    public Alarm insert(Alarm alarm);
    public List<Alarm> getNotReadListByUserId(Long userId);
    public void updateStatusById(Long id, AlarmStatus status);
}
