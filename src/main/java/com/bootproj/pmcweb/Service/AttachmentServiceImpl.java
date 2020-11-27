package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Mapper.AccountMapper;
import com.bootproj.pmcweb.Mapper.AttachmentMapper;
import com.bootproj.pmcweb.Network.Exception.FileDeleteException;
import com.bootproj.pmcweb.Network.Exception.FileSaveException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Log4j2
@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    private Environment env;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Optional<Attachment> getProfile(String email) {
        // 이메일로 이미지 경로 아이디 가져오기

        // 이미지 경로 객체 반환하기

        return null;
    }

    @Transactional
    @Override
    public Attachment uploadProfile(MultipartFile file, String email) throws FileSaveException{
        Properties properties = new Properties();
        // 기존에 있던 이미지 삭제
        deleteProfile(email);

        // 이미지 업로드
        fileUpload(file, env.getProperty("profile.image.path") + file.getOriginalFilename());

        // 이미지 경로 DB에 추가하기
        Attachment attachment = Attachment.builder()
                .name(file.getOriginalFilename())
                .path(env.getProperty("profile.image.path") + file.getOriginalFilename())
                .build();
        attachmentMapper.insert(attachment);

        // 유저 DB에 이미지 경로 추가하기
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("attachment_id", Long.toString(attachment.getId()));
        accountMapper.updateUserAttachment(map);

        return attachment;
    }

    @Transactional
    @Override
    public void deleteProfile(String email) throws FileDeleteException, IllegalArgumentException {
        // 유저 DB에 연결된 이미지 경로 가져오기
        Account account = accountMapper.getUserByEmail(email);

        // 유저 DB에 이미지 경로 삭제하기
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("attachment_id", "");
        accountMapper.updateUserAttachment(map);

        // 이미지 경로 가져오기
        Optional<Attachment> attachment = attachmentMapper.findById(account.getAttachmentId());
        attachment.ifPresentOrElse(
                (att) -> {
                    // 이미지 경로 DB에서 지우기
                    attachmentMapper.deleteById(account.getAttachmentId());
                    // 파일 삭제
                    deleteFile(att.getPath());
                },

                () -> {
                    throw new IllegalArgumentException("이미지 경로가 존재하지 않습니다.");
                });


    }

    private void fileUpload(MultipartFile file, String path) throws FileSaveException{
        // DB에 byte형식으로 저장하는 방식을 고려해 볼 것.
        try {
            FileOutputStream fos = new FileOutputStream(path);
            InputStream is = file.getInputStream();
            String originFilename = file.getOriginalFilename();
            String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
            int readCount = 0; // 읽음 성공 여부 저장용
            byte[] buffer = new byte[1024]; // 1024 바이트씩 읽어서 파 일 저장

            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception ex) {
            throw new FileSaveException(ex.getMessage());
        }
    }

    private void deleteFile(String path) {
        try {

        } catch (Exception e){
            throw new FileDeleteException(e.getMessage());
        }
    }
}
