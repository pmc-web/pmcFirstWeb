package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.StudyMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Mapper
public interface StudyMaterialMapper {
    public List<StudyMaterial> getListAll();
    public List<StudyMaterial> getListByStudyId(Long studyId);
    public Optional<StudyMaterial> getByAttachmentId(Long attachmentId);
    public Optional<StudyMaterial> getById(Long id);
    public void insert(StudyMaterial studyMaterial);
    public void insertBatch(List<StudyMaterial> studyMaterials);
    public void deleteById(Long studyMaterialId);
    public void deleteByStudyId(Long studyId);
    public void update(StudyMaterial studyMaterial);
    public void updateBatch(List<StudyMaterial> studyMaterials);
}
