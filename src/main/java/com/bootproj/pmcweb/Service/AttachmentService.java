package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Network.Exception.FileDeleteException;
import com.bootproj.pmcweb.Network.Exception.FileSaveException;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

public interface AttachmentService {
    public Optional<Attachment> getAttachment(Long attachmentId);
    public Optional<Attachment> getProfile(String email);
    public Attachment uploadProfile(MultipartFile file, String email) throws FileSaveException, NoSuchElementException;
    public void deleteProfile(String email) throws FileDeleteException, IllegalArgumentException;
    public void updateAttachment(Long attachmentId, String path, String name) throws IllegalArgumentException;
//    public Optional<Attachment> getStudyAttachment(Long studyId);
//    public Attachment uploadStudyAttachment(MultipartFile file, Long studyId) throws FileSaveException;
//    public void deleteStudyAttachment(Long studyId) throws FileDeleteException, IllegalArgumentException;

}
