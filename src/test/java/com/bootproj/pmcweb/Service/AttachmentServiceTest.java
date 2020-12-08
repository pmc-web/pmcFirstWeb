package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.PmcwebApplication;
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

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
public class AttachmentServiceTest {

    @Autowired
    AttachmentService attachmentService;

    @Test
    void getProfile() {
    }

    @Test
    void uploadProfile() {
    }

    @Test
    void deleteProfile() {
    }

    @Test
    void getStudyAttachment() {
        Long studyId = 3L;
        List<Attachment> attachments = attachmentService.getStudyAttachments(studyId);
        log.info(attachments);
        Assert.assertTrue(attachments.size()>0);
    }
}
