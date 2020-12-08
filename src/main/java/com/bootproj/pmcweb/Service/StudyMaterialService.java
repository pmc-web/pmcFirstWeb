package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.StudyMaterial;

import java.util.List;
import java.util.Optional;

public interface StudyMaterialService {
    public StudyMaterial insert(StudyMaterial studyMaterial);
    public List<StudyMaterial> insertBatch(List<StudyMaterial> studyMaterials);
    public Optional<StudyMaterial> get(Long id);
    public List<StudyMaterial> getList();
    public List<StudyMaterial> getListByStudyId(Long studyId);
    public Optional<StudyMaterial> getByAttachmentId(Long attachmentId);
    public void delete(Long id);
    public void deleteByStudyId(Long studyId);
    public StudyMaterial update(StudyMaterial studyMaterial);
    public List<StudyMaterial> updateBatch(List<StudyMaterial> studyMaterials);
}
