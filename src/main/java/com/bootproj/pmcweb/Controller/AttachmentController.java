package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Network.Exception.FileSaveException;
import com.bootproj.pmcweb.Service.AttachmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@Log4j2
@RestController("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/uploadProfile")
    public String upload(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file){
        // try catch
        attachmentService.uploadProfile(file, user.getUsername());
        return "redirect:/user/profile";
    }
}
