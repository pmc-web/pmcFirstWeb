package com.bootproj.pmcweb.Config;

import com.bootproj.pmcweb.Common.Request.LoginFailHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리 하위 목록은 무시
        // csrf에 의해서 보호하지 않음
        web.ignoring().antMatchers("/css/**", "/img/**","/vendor/**","/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .csrf().disable()
            .authorizeRequests()
            // 페이지 권한 설정
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/","/user/signup", "/user/login", "/user/sendSignUpEmail", "/user/signUpConfirm","/study/**","/ws-stomp/**","/chat/**", "/out/img/**","/search/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/user/login")
            .defaultSuccessUrl("/")
            .failureHandler(failureHandler())
//            .successHandler(successHandler())
            .permitAll()
            .and() // 로그아웃 설정
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll();
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);

    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new LoginFailHandler();
    }

//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new LoginSuccessHandler();
//    }
}
