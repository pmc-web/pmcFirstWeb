package com.bootproj.pmcweb.Network.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler {
    private final String DEFAULT_FAILURE_URL = "/user/loginFail";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = null;

        if(exception instanceof BadCredentialsException){
            errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
        }else if(exception instanceof DisabledException) {
            errorMsg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
        }else if(exception instanceof LockedException){
            log.info("이메일이 인증되지 않았습니다. 이메일을 확인해 주세요.");
            errorMsg = "이메일이 인증되지 않았습니다. 이메일을 확인해 주세요.";
        }else{
            errorMsg = "알수없는 이유로 로그인에 실패하였습니다.";
        }
        request.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }
}