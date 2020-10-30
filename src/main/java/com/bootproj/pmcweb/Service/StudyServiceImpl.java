package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Mapper.StudyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor // 생성자를 통해 DI
@Service
public class StudyServiceImpl implements StudyService {
//    final private StudyMapper studyMapper; //final : 생성될때 초기화

    public List<Study> selectStudyList() {
        return null;
//        return studyMapper.getStudyList();
    }

    @Override
    public Study createStudy(Study study) {
        return null;
//        return studyMapper.insertStudy(study);
    }
}
