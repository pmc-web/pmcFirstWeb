package com.bootproj.pmcweb.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bootproj.pmcweb.Service.AccountSecurityService;
import com.bootproj.pmcweb.Service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountSecurityService accountSecurityService;

    /* ------------------ Web ------------------- */
    @Test
    public void getSignup() throws Exception {
        this.mockMvc.perform(get("/user/signup"))
                .andDo(print())
                .andExpect(result -> "user/register".equals(result))
                .andExpect(status().isOk());
    }

    @Test
    public void getLogin() throws Exception {
        this.mockMvc.perform(get("/user/login"))
                .andDo(print())
                .andExpect(result -> "user/login".equals(result))
                .andExpect(status().isOk());
    }


    /* ------------------ Rest API ------------------- */

    // TODO: Security에서 현재 로그인한 유저 정보를 mock으로 가져와야 함.
    @Test
    public void getLoginSuccess() throws Exception{

    }

    @Test
    void getLogout() throws Exception {
//        this.mockMvc.perform(get("/user/logout"))
//                .andDo(print())
//                .andExpect(result -> "user/login".equals(result))
//                .andExpect(status().isOk());
    }

    @Test
    void getProfile() {
    }

    @Test
    void changePassword() throws Exception {
        this.mockMvc.perform(get("/user/changePassword"))
                .andDo(print())
                .andExpect(result -> "user/changePassword".equals(result))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() {

    }

    @Test
    void getUser() {
    }

    @Test
    void sendSignUpEmail() {
    }

    @Test
    void signUpConfirm() {
    }
}