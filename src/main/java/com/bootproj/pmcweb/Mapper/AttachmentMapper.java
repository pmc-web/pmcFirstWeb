package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Mapper
public interface AttachmentMapper {
    public List<Attachment> getList();
    public Optional<Attachment> findById(Long id);
    public Optional<Attachment> findByFile(Map<String, String> map);
    public void insert(Attachment attachment);
    public void deleteById(Long id);
    public void updateById(Map<String, String> map);
    public void deleteByFile(Map<String, String> map);
    public void updateByFile(Map<String, String> map);
}