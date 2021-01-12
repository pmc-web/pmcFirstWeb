package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.StudyMaterial;
import com.bootproj.pmcweb.Mapper.StudyMaterialMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudyMaterialServiceImpl implements StudyMaterialService {

    private final StudyMaterialMapper studyMaterialMapper;

    @Override
    public StudyMaterial insert(StudyMaterial studyMaterial) {
        return null;
    }

    @Override
    public List<StudyMaterial> insertBatch(List<StudyMaterial> studyMaterials) {
        return null;
    }

    @Override
    public Optional<StudyMaterial> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<StudyMaterial> getList() {
        return null;
    }

    @Override
    public List<StudyMaterial> getListByStudyId(Long studyId) {
        return null;
    }

    @Override
    public Optional<StudyMaterial> getByAttachmentId(Long attachmentId) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteByStudyId(Long studyId) {

    }

    @Override
    public StudyMaterial update(StudyMaterial studyMaterial) {
        return null;
    }

    @Override
    public List<StudyMaterial> updateBatch(List<StudyMaterial> studyMaterials) {
        return null;
    }
}
