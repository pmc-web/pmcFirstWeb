package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.CustomUserDetails;
import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Override
    // 상세정보를 조회하는 메서드
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.getUserByEmail(email);
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        if(user == null){
            log.debug("# 가입한 이메일이 존재하지 않습니다. #");
            throw new UsernameNotFoundException(email);
        }

//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        if(("admin@example.com").equals(email)){
//            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getTitle()));
//        }

        return customUserDetails;
    }
}
