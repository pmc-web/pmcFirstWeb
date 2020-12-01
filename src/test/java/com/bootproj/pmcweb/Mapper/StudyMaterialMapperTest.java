package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMaterial;
import com.bootproj.pmcweb.Domain.enumclass.StudyMaterialType;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyMaterialMapperTest {

    Long testId = 4L;
    Long testStudyId = 3L;
    Long testAttachmentId = 44L;


    @Autowired
    StudyMaterialMapper studyMaterialMapper;

    @Test
    void getListAll() {
        List<StudyMaterial> studyMaterials = studyMaterialMapper.getListAll();
        log.info(studyMaterials);
        Assert.assertTrue(studyMaterials.size()>0);
    }

    @Test
    void getListByStudyId() {
        List<StudyMaterial> studyMaterials = studyMaterialMapper.getListByStudyId(testStudyId);
        log.info(studyMaterials);
        Assert.assertTrue(studyMaterials.size()>0);
    }


    @Test
    void getByAttachmentId() {
        Optional<StudyMaterial> studyMaterialOptional = studyMaterialMapper.getByAttachmentId(testAttachmentId);
        Assert.assertTrue(studyMaterialOptional.isPresent());
    }

    @Test
    void getById() {
        Optional<StudyMaterial> studyMaterialOptional = studyMaterialMapper.getById(testId);
        Assert.assertTrue(studyMaterialOptional.isPresent());
    }

    @Test
    void insert(){
        StudyMaterial studyMaterial = StudyMaterial.builder()
                .attachmentId(testAttachmentId)
                .studyId(testStudyId)
                .type(StudyMaterialType.IMAGE.getTitle())
                .build();
        studyMaterialMapper.insert(studyMaterial);
        log.info(studyMaterial);
        Assert.assertNotNull(studyMaterial.getId());
    }

    @Test
    void insertBatch() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteByIdBatch() {
    }

    @Test
    void update() {
    }

    @Test
    void updateBatch() {
    }

}
