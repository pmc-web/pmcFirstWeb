package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper subjectMapper;

    @Override
    public List<String> getSubjectDepth1() {
        return subjectMapper.selectSubjectDepth1();
    }

    @Override
    public List<String> getSubjectDepth2(String subjectDepth1) {
        return subjectMapper.selectSubjectDepth2(subjectDepth1);
    }
}
