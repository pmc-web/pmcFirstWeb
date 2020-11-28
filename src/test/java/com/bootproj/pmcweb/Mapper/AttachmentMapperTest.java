package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Attachment;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@MybatisTest
public class AttachmentMapperTest {
    final static String path = "/test";
    final static String name = "test.jpg";
    Long testId = 30L;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Test
    public void insert(){
        Attachment attachment = Attachment.builder()
                .path(path)
                .name(name)
                .build();
        attachmentMapper.insert(attachment);
        log.info(attachment.toString());
        Assert.assertNotNull(attachment.getId());
    }

    @Test
    public void findById(){
        Optional<Attachment> attachment = attachmentMapper.findById(testId);
        log.info(attachment.toString());
        Assert.assertTrue(attachment.isPresent());
    }

    @Test
    public void deleteById(){
        Optional<Attachment> attachment = attachmentMapper.findById(testId);
        Assert.assertTrue(attachment.isPresent());
        attachmentMapper.deleteById(testId);
        Optional<Attachment> deletedAttachment = attachmentMapper.findById(testId);
        Assert.assertFalse(deletedAttachment.isPresent());
    }

    @Test
    void updateById() {
        Map<String, String> map = new HashMap<>();
        map.put("path", path + "updated ");
        map.put("name", name + "updated");
        map.put("id", Long.toString(testId));

        attachmentMapper.updateById(map);
        Optional<Attachment> updatedAttachment = attachmentMapper.findById(testId);
        log.info(updatedAttachment);
        updatedAttachment.ifPresent(attachment ->{
            Assert.assertEquals(path + "updated ", attachment.getPath());
            Assert.assertEquals(name + "updated", attachment.getName());
        });
    }

    @Test
    void deleteByFile() {
        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        map.put("name", name);

        Optional<Attachment> attachment = attachmentMapper.findByFile(map);
        Assert.assertTrue(attachment.isPresent());
        attachmentMapper.deleteByFile(map);
        Optional<Attachment> deletedAttachment = attachmentMapper.findByFile(map);
        Assert.assertFalse(deletedAttachment.isPresent());
    }

    @Test
    void updateByFile() {
        Map<String, String> map = new HashMap<>();
        map.put("path", path + "updated ");
        map.put("name", name + "updated");

        attachmentMapper.updateByFile(map);
        Optional<Attachment> updatedAttachment = attachmentMapper.findByFile(map);
        log.info(updatedAttachment);
        updatedAttachment.ifPresent(attachment ->{
            Assert.assertEquals(path + "updated ", attachment.getPath());
            Assert.assertEquals(name + "updated", attachment.getName());
        });
    }
}
