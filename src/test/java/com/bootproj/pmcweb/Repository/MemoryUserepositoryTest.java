//package com.bootproj.pmcweb.Repository;
//
//import com.bootproj.pmcweb.Domain.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class MemoryUserRepositoryTest {
//
//    MemoryUserRepository repository = new MemoryUserRepository();
//
//    @AfterEach
//    public void afterEach(){
//        repository.clearStore();
//    }
//
//    @Test
//    public void save() {
//        User member = new User();
//        member.setName("spring");
//
//        repository.save(member);
//
//        User result = repository.findById(member.getId()).get();
////        Assertions.assertEquals(member, null);
//        assertThat(member).isEqualTo(result);
//    }
//
//    @Test
//    public void findByEmail() {
//        User user1 = new User();
//        user1.setEmail("spring1@naver.com");
//        repository.save(user1);
//
//        User user2 = new User();
//        user2.setEmail("spring2@naver.com");
//        repository.save(user2);
//
//        User result = repository.findByEmail("spring1@naver.com").get();
//
//        assertThat(result).isEqualTo(user1);
//    }
//
//    @Test
//    public void findAll(){
//        User user1 = new User();
//        user1.setEmail("spring1@naver.com");
//        repository.save(user1);
//
//        User member2 = new User();
//        member2.setEmail("spring2@naver.com");
//        repository.save(member2);
//
//        List<User> result = repository.findAll();
//
//        assertThat(result.size()).isEqualTo(2);
//    }
//}
