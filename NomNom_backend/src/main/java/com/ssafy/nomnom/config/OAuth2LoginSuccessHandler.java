package com.ssafy.nomnom.config;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nomnom.model.dao.UserDao;
import com.ssafy.nomnom.model.dto.user.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 소셜 로그인(Google) 성공 시 호출되는 핸들러
 * - 로그인한 사용자 정보로 DB에 회원 등록 (없으면 자동 회원가입)
 * - JWT 토큰을 생성해서 클라이언트에 응답
 */
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider; // JWT 생성기
    private final UserDao userDao; // 사용자 DB 처리용 DAO

    public OAuth2LoginSuccessHandler(JwtTokenProvider jwtTokenProvider, UserDao userDao) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //소셜 로그인 사용자 정보 받아오기
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oauthUser.getAttributes();

        //이메일 정보 추출
        String email = (String) attributes.get("email");

        //DB에 사용자 있는지 확인
        User user = userDao.getUserByEmail(email);
        
        boolean isNewUser = false;

        //없다면 신규 사용자로 등록
        if (user == null) {
            user = new User();
            user.setUserId("google_" + email);  // 구글 계정 기반 ID
            user.setUserEmail(email);           // 구글 이메일
            user.setUserPw("social_login");     // 패스워드는 더미 값
            user.setUserRole("USER");           // 기본 권한
            user.setUserBirthday(LocalDate.of(1800, 1, 1)); //생일은 필수값이라 임시로 설정 

            userDao.insertUser(user);           // DB에 저장

            // 저장한 사용자 다시 조회 (user_no 등 PK 포함해서)
            user = userDao.getUserByEmail(email);
            
            isNewUser = true;
        }

        //JWT 토큰 발급
        String token = jwtTokenProvider.createToken(user.getUserEmail(), user.getUserRole());
        
        //응답: token + isNewUser 여부
        Map<String, Object> result = Map.of(
            "token", token,
            "isNewUser", isNewUser
        );

        //응답으로 JWT 전달 (프론트 없을 경우 JSON 형태)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}
