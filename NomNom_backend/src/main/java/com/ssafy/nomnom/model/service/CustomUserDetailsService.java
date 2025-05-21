package com.ssafy.nomnom.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.nomnom.model.dao.UserDao;
import com.ssafy.nomnom.model.dto.user.User;

@Service //이 클래스를 스프링 서비스 빈으로 등록
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    //생성자 주입으로 UserDao 사용
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    //이메일로 사용자 조회 (Spring Security 내부에서 호출됨)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // DB에서 사용자 정보 조회
        User user = userDao.getUserByEmail(email);

        // 사용자 없으면 예외 발생
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        //사용자 정보를 Spring Security 전용 User 객체로 반환
        return new org.springframework.security.core.userdetails.User(
            user.getUserEmail(),  // username
            user.getUserPw(),     // password
            Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getUserRole()) //권한 형식은 ROLE_접두어 필요
            )
        );
    }
}
