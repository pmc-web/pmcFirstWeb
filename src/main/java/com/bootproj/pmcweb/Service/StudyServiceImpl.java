package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Request.StudyCreateRequest;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Mapper.StudyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor // 생성자를 통해 DI
@Service
public class StudyServiceImpl implements StudyService {
    final private StudyMapper studyMapper; //final : 생성될때 초기화

    @Override
    public List<Study> getStudyList(Integer page) {
        Integer limit = 10;
        Integer offset = limit*(page-1);
        // TODO : pagination
        return studyMapper.getStudyList(limit, offset);
    }

    @Override
    public Long createStudy(StudyCreateRequest study) {
        int result =  studyMapper.insertStudy(study);
        System.out.println(study.toString());
        if(result == 1){
            return study.getId();
        }else {
            return 0L;
        }
    }

    @Override
    public Optional<Study> getStudyDetail(Long studyId) {
        return studyMapper.getStudyDetail(studyId);
    }

    @Override
    public Optional<StudyApiResponse> getStudyInfo(Long studyId) {
        return studyMapper.getStudyInfoDetail(studyId);
    }

    @Override
    public Optional<Study> putStudyStatus(Long studyId, String status) {
        studyMapper.putStudyStatus(studyId, status);
        Optional<Study> study = studyMapper.getStudyDetail(studyId);
        return study;
    }

    @Override
    public List<String> getAllList() {
        return studyMapper.getStudyAllList();
    }

}
