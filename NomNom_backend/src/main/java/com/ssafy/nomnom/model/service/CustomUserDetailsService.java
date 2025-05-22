package com.ssafy.nomnom.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.nomnom.model.dao.UserDao;
import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.dto.user.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // DB에서 사용자 정보 조회
        User user = userDao.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        //CustomUserDetails 객체로 반환
        return new CustomUserDetails(
            user.getUserNo(),
            user.getUserEmail(),
            user.getUserPw(),
            user.getUserRole()
        );
    }
}
