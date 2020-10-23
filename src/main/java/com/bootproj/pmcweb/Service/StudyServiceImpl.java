package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {

    //    @Autowired
    @Resource
    private StudyMapper studyMapper;

    public List<Study> selectStudyList() {
        return studyMapper.getStudyList();
    }
}
