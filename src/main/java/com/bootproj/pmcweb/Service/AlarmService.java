package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Response.AlarmApiResponse;
import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;

import java.util.List;

public interface AlarmService {
    public Alarm insert(Alarm alarm);
    public List<AlarmApiResponse> getNotReadListByUserId(Long userId);
    public List<AlarmApiResponse> getListByUserId(Long userId);
    public void updateStatusById(Long id, AlarmStatus status);
}
