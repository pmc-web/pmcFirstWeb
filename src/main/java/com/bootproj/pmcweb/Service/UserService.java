package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository memberRepository) {
        this.userRepository = memberRepository;
    }

    /**
     *  회원 가입
     */
    public Long join(User user){

        validateDuplicateMember(user); // 중복 회원 검증

        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User member) {
        userRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findMembers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long memberId){
        return userRepository.findById(memberId);
    }
}
