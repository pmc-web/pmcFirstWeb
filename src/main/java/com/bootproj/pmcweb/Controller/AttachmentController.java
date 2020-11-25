package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Network.Exception.FileSaveException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@Log4j2
@RestController("/attachment")
public class AttachmentController {
    //TODO: 파일 저장 경로 properties로 옮길 것.
    final static String filePath = "C:\\Users\\jiae\\git\\pmcFirstWeb\\src\\main\\resources\\static\\img\\profiles\\";

    @PostMapping("/uploadProfile")
    public String upload(@RequestParam("file") MultipartFile file){
        // TODO: 서비스단으로 옮기기
        // TODO: Attachment DB와 연동해서 어떤 유저에 해당하는 파일인지 추가할 것.
        try (
                FileOutputStream fos = new FileOutputStream(filePath + file.getOriginalFilename());
                InputStream is = file.getInputStream();
        ) {
            String originFilename = file.getOriginalFilename();
            String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
            int readCount = 0; // 읽음 성공 여부 저장용
            byte[] buffer = new byte[1024]; // 1024 바이트씩 읽어서 파 일 저장

            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception ex) {
            throw new FileSaveException("파일 저장에 실패했습니다.");
        }
        return "redirect:/user/profile";
    }
}
