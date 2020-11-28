package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Network.Exception.FileSaveException;
import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Network.ResultCode;
import com.bootproj.pmcweb.Service.AttachmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@Log4j2
@RestController
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/profile/upload")
    public ResponseEntity<Header<Attachment>> upload(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file){
        Attachment attachment;
        try {
            attachment = attachmentService.uploadProfile(file, user.getUsername());
        } catch (Exception e) {
            throw new FileSaveException(e.getMessage());
        }
        return new ResponseEntity(Header.OK(attachment), HttpStatus.CREATED);
    }
}
