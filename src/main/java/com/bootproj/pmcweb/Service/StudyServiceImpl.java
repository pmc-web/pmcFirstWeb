package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Mapper.StudyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // 생성자를 통해 DI
@Service
public class StudyServiceImpl implements StudyService {
    final private StudyMapper studyMapper; //final : 생성될때 초기화

    public List<Study> selectStudyList() {
        return studyMapper.getStudyList();
    }

    @Override
    public Long createStudy(Study study) {
        int result =  studyMapper.insertStudy(study);
        System.out.println(study.toString());
        if(result == 1){
            return study.getId();
        }else {
            return 0L;
        }
    }

    @Override
    public Study getStudyDetail(Long studyId) {
        return studyMapper.getStudyDetail(studyId);
    }

    @Override
    public Study putStudyStatus(Long studyId, String status) {
        return studyMapper.putStudyStatus(studyId, status);
    }

}
