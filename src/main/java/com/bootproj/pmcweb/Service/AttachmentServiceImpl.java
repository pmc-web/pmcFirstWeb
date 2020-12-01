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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public Optional<Attachment> getAttachment(Long attachmentId) {
        return attachmentMapper.findById(attachmentId);
    }

    @Override
    public Optional<Attachment> getProfile(String email) {
        // 이메일로 이미지 경로 아이디 가져오기
        Account account = accountMapper.getUserByEmail(email);

        // 이미지 경로 객체 반환하기
        return attachmentMapper.findById(account.getAttachmentId());
    }

    @Transactional
    @Override
    public Attachment uploadProfile(MultipartFile file, String email) throws FileSaveException, NoSuchElementException{

        // account, path 값 초기화
        Attachment attachment;
        Account account = accountMapper.getUserByEmail(email);
        String pathOnly = env.getProperty("profile.image.path") + File.separator + account.getId();
        String path = pathOnly + File.separator + file.getOriginalFilename();

        if (account.getAttachmentId()!=null){
            // 기존에 값이 있으면 Attachment 정보 업데이트
            updateAttachment(account.getAttachmentId(), path, file.getOriginalFilename());
            attachment = attachmentMapper.findById(account.getAttachmentId()).orElseThrow(() -> new NoSuchElementException());
        } else {
            // 기존에 값이 없으면 새로 생성
            // 프로필 폴더 생성
            try {
                File folder = new File(pathOnly);
                if (!folder.exists()){
                    folder.mkdirs();
                    log.info("프로필 폴더 생성 완료");
                }
            } catch (Exception e){
                throw new FileSaveException(e.getMessage());
            }

            // 이미지 경로 DB에 추가하기
            attachment = Attachment.builder()
                    .name(file.getOriginalFilename())
                    .path(path)
                    .build();
            attachmentMapper.insert(attachment);

            // 유저 DB에 이미지 경로 추가하기
            Map<String, String> map = new HashMap<>();
            map.put("email", email);
            map.put("attachmentId", Long.toString(attachment.getId()));
            accountMapper.updateUserAttachment(map);
        }

        // 이미지 업로드
        fileUpload(file, path);

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
        attachment.ifPresent(
                (att) -> {
                    // 이미지 경로 DB에서 지우기
                    attachmentMapper.deleteById(account.getAttachmentId());
                    // 파일 삭제
                    deleteFile(att.getPath());
                });
    }

    @Transactional
    @Override
    public void updateAttachment(Long attachmentId, String path, String name) throws IllegalArgumentException {
        // 이미지 경로 가져오기
        Optional<Attachment> attachment = attachmentMapper.findById(attachmentId);
        attachment.ifPresent(
                (att) -> {
                    // 이미지 경로 DB에서 업데이트하기
                    Map<String, String> map = new HashMap<>();
                    map.put("id", Long.toString(attachmentId));
                    map.put("path", path);
                    map.put("name", name);
                    attachmentMapper.updateById(map);
                    // 파일 삭제
                    deleteFile(att.getPath());
                });
    }

//    @Override
//    public Optional<Attachment> getStudyAttachment(Long studyId) {
//        Study study = studyMapper.getStudyDetail(studyId);
//        if (study!=null){
//            return attachmentMapper.findById(study.getAttachmentId());
//        } else {
//            return Optional.empty();
//        }
//    }
//
//    @Transactional
//    @Override
//    public Attachment uploadStudyAttachment(MultipartFile file, Long studyId) throws FileSaveException {
//        // 기존에 값이 있으면 이미지 삭제
////        Study study = studyMapper.getStudyDetail(studyId);
////        if (study.getAttachmentId()!=null) deleteStudyAttachment(studyId);
//
//        // 스터디 첨부파일 폴더 생성
//        try {
//            File folder = new File(env.getProperty("study.image.path") + File.separator + studyId);
//            if (!folder.exists()){
//                folder.mkdirs();
//                log.info("스터디 첨부파일 폴더 생성 완료");
//            }
//        } catch (Exception e){
//            throw new FileSaveException(e.getMessage());
//        }
//
//        // 이미지 업로드
//        fileUpload(file, env.getProperty("study.image.path") + File.separator + studyId + File.separator + file.getOriginalFilename());
//
//        // 이미지 경로 DB에 추가하기
//        Attachment attachment = Attachment.builder()
//                .name(file.getOriginalFilename())
//                .path(env.getProperty("study.image.path") + File.separator + studyId + File.separator + file.getOriginalFilename())
//                .build();
//        attachmentMapper.insert(attachment);
//
//        // 유저 DB에 이미지 경로 추가하기
//        Map<String, String> map = new HashMap<>();
//        map.put("id", Long.toString(studyId));
//        map.put("attachmentId", Long.toString(attachment.getId()));
//        studyMapper.updateAttachmentId(studyId, attachment.getId());
//
//        return attachment;
//    }
//
//    @Transactional
//    @Override
//    public void deleteStudyAttachment(Long studyId) throws FileDeleteException, IllegalArgumentException {
//        // 스터디 DB에 연결된 이미지 경로 가져오기
//        Study study = studyMapper.getStudyDetail(studyId);
//        studyMapper.updateAttachmentId(studyId, null);
//
//        // 이미지 경로 가져오기
//        Optional<Attachment> attachment = attachmentMapper.findById(study.getAttachmentId());
//        attachment.ifPresent(
//                (att) -> {
//                    // 이미지 경로 DB에서 지우기
//                    attachmentMapper.deleteById(study.getAttachmentId());
//                    // 파일 삭제
//                    deleteFile(att.getPath());
//                });
//    }

    private void fileUpload(MultipartFile file, String path) throws FileSaveException{
        // DB에 byte형식으로 저장하는 방식을 고려해 볼 것.

        // 파일 저장
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

            fos.close();
        } catch (Exception ex) {
            throw new FileSaveException(ex.getMessage());
        }
    }

    private void deleteFile(String path) throws FileDeleteException{
        try {
            File file = new File(path);
            if (file.exists()){
                file.delete();
            } else {
                throw new FileDeleteException("기존 파일이 존재하지 않습니다.");
            }
        } catch (Exception e){
            throw new FileDeleteException(e.getMessage());
        }
    }
}
