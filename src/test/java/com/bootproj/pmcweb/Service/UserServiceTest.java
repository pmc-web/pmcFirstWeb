//package com.bootproj.pmcweb.Service;
//
//import com.bootproj.pmcweb.Domain.User;
//import com.bootproj.pmcweb.Repository.MemoryUserRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserServiceTest {
//
//    UserService userService;
//    MemoryUserRepository userRepository;
//
//    @BeforeEach
//    public void beforeEach(){
//        userRepository = new MemoryUserRepository();
//        userService = new UserService(userRepository);
//    }
//
//    @AfterEach
//    public void afterEach(){
//        userRepository.clearStore();
//    }
//
//    @Test
//    void 회원가입() {
//        //given
//        User member = new User();
//        member.setName("hello");
//
//        //when
//        Long saveId = userService.join(member);
//
//        //then
//        User findMember = userService.findOne(saveId).get();
//        assertThat(member.getName()).isEqualTo(findMember.getName());
//    }
//
//    @Test
//    public void 중복_회원_예외() {
//        //given
//        User member1 = new User();
//        member1.setEmail("spring@naver.com");
//
//        User member2 = new User();
//        member2.setEmail("spring@naver.com");
//
//        //when
//        userService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(member2));
//
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
////        try{
////            memberService.join(member2);
////            fail();
////        }catch(IllegalStateException e) {
////            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
////        }
//
//        //then
//    }
//
//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
//}
