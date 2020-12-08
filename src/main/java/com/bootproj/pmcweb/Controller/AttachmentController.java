package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Domain.enumclass.StudyMaterialType;
import com.bootproj.pmcweb.Common.Exception.FileSaveException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Service.AttachmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/profile")
    public ResponseEntity<Header<Attachment>> uploadProfile(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file){
        Attachment attachment;
        try {
            attachment = attachmentService.uploadProfile(file, user.getUsername());
        } catch (Exception e) {
            throw new FileSaveException(e.getMessage());
        }
        return new ResponseEntity(Header.OK(attachment), HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<Header<Attachment>> getProfile(@AuthenticationPrincipal User user) throws NoMatchingAcountException {
        Optional<Attachment> attachment = attachmentService.getProfile(user.getUsername());
        attachment.orElseThrow(() -> new NoMatchingAcountException("해당하는 유저의 프로필이 없습니다."));
        return new ResponseEntity(Header.OK(attachment.get()), HttpStatus.OK);
    }

    @PostMapping("/study/{studyId}")
    public ResponseEntity<Header<Object>> uploadStudyAttachment(@PathVariable("studyId") Long studyId, @RequestParam String type, @RequestParam("file") MultipartFile file){
        try {
            if (StudyMaterialType.MAIN_IMAGE.getTitle().equals(type)) {
                attachmentService.uploadStudyMainImage(file, studyId);
            } else {
                throw new IllegalArgumentException("잘못된 타입입니다.");
            }
        } catch (Exception e) {
            throw new FileSaveException(e.getMessage());
        }
        return new ResponseEntity(Header.OK(), HttpStatus.CREATED);
    }

    @GetMapping("/study/{studyId}")
    public ResponseEntity<Header<Attachment>> getStudyAttachment(@PathVariable("studyId") Long studyId) throws NoMatchingAcountException {
        Optional<Attachment> attachment = attachmentService.getStudyMainImage(studyId);
        attachment.orElseThrow(() -> new NoMatchingAcountException("해당하는 스터디 메인 이미지 파일이 없습니다."));
        return new ResponseEntity(Header.OK(attachment.get()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Header<Attachment>> getAttachment(@PathVariable("id") Long id) throws NoMatchingAcountException {
        Optional<Attachment> attachment = attachmentService.getAttachment(id);
        attachment.orElseThrow(() -> new NoMatchingAcountException("해당하는 파일이 없습니다."));
        return new ResponseEntity(Header.OK(attachment.get()), HttpStatus.OK);
    }
}
