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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyMaterialMapperTest {

    Long testId = 14L;
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
        List<StudyMaterial> studyMaterials = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            StudyMaterial studyMaterial = StudyMaterial.builder()
                    .attachmentId(testAttachmentId)
                    .studyId(testStudyId)
                    .type(StudyMaterialType.IMAGE.getTitle())
                    .build();
            studyMaterials.add(studyMaterial);
        }
        studyMaterialMapper.insertBatch(studyMaterials);
        log.info(studyMaterials);
        for (int i = 0; i <3; i++){
            Assert.assertNotNull(studyMaterials.get(i).getId());
        }
    }

    @Test
    void deleteById() {
        Optional<StudyMaterial> studyMaterialOptional = studyMaterialMapper.getById(testId);
        Assert.assertTrue(studyMaterialOptional.isPresent());

        studyMaterialMapper.deleteById(testId);

        Optional<StudyMaterial> deletedStudyMaterialOptional = studyMaterialMapper.getById(testId);
        Assert.assertFalse(deletedStudyMaterialOptional.isPresent());
    }

    @Test
    void deleteByStudyId() {
        List<StudyMaterial> studyMaterials = studyMaterialMapper.getListByStudyId(testStudyId);
        log.info(studyMaterials);
        studyMaterialMapper.deleteByStudyId(testStudyId);

        List<StudyMaterial> deletedStudyMaterials = studyMaterialMapper.getListByStudyId(testStudyId);
        log.info(deletedStudyMaterials);
        Assert.assertEquals(0, deletedStudyMaterials.size());
    }

    @Test
    void update() {
        StudyMaterial studyMaterial = StudyMaterial.builder()
                .id(testId)
                .attachmentId(testAttachmentId)
                .studyId(testStudyId)
                .type(StudyMaterialType.FILE.getTitle())
                .build();
        log.info(studyMaterial);

        studyMaterialMapper.update(studyMaterial);
        studyMaterialMapper.getById(studyMaterial.getId()).ifPresent(att->{
            Assert.assertEquals(studyMaterial.getType(), att.getType());
        });

    }

    @Test
    void updateBatch() {
        List<StudyMaterial> studyMaterials = new ArrayList<>();
        String type = StudyMaterialType.IMAGE.getTitle();
        for (int i = 0; i < 3; i++){
            StudyMaterial studyMaterial = StudyMaterial.builder()
                    .id(testId + i)
                    .attachmentId(testAttachmentId)
                    .studyId(testStudyId)
                    .type(type)
                    .build();
            studyMaterials.add(studyMaterial);
        }
        log.info(studyMaterials);

        studyMaterialMapper.updateBatch(studyMaterials);
        log.info(studyMaterials);

        for (int i = 0; i <3; i++){
            Assert.assertEquals(type, studyMaterials.get(i).getType());
        }
    }

}
