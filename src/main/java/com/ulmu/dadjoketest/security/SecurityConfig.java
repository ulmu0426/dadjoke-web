package com.ulmu.dadjoketest.security;

import com.ulmu.dadjoketest.domain.User;
import com.ulmu.dadjoketest.repository.UserRepository;
import com.ulmu.dadjoketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.DispatcherType;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements UserDetailsService{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //접근권한 설정
        http.authorizeHttpRequests()
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .antMatchers("/login","/join","/joke/**","/joke/comment/**").permitAll()    //로그인,회원가입,게시판,게시판댓글 페이지 권한 허가
                .anyRequest().permitAll();  //임시로 모든 요청 허용

        //사이트 위변조 요청 방지
        http.csrf().disable();

        //로그인 설정
        http.formLogin()
                .loginPage("/login")        //로그인페이지
                .loginProcessingUrl("/login-process")   //submit 요청을 받을 url
                .defaultSuccessUrl("/")     //로그인 성공시 기본페이지로
                .failureUrl("/login")    //로그인 실패시 다시 로그인 페이지로
                .usernameParameter("userId")
                .passwordParameter("password");

        //로그아웃 설정
        http.logout()
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        //사용자 인증 처리 컴포넌트 서비스 등록
        http.userDetailsService((UserDetailsService) userService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> res = userRepository.findByName(username);
        User user = res.orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
