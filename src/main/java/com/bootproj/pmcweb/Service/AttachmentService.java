package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Network.Exception.FileDeleteException;
import com.bootproj.pmcweb.Network.Exception.FileSaveException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface AttachmentService {
    public Optional<Attachment> getProfile(String email);
    public Attachment uploadProfile(MultipartFile file, String email) throws FileSaveException;
    public void deleteProfile(String email) throws FileDeleteException, IllegalArgumentException ;
}
