package com.ssafy.nomnom.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nomnom.config.JwtTokenProvider;
import com.ssafy.nomnom.model.dto.user.LoginRequest;
import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.service.UserService;

/**
 * 인증 관련 API를 처리하는 컨트롤러
 * - 로그인 (이메일/비밀번호 기반)
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 생성자 주입
    public AuthController(JwtTokenProvider provider, UserService service, PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = provider;
        this.userService = service;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * [POST] /api/auth/login
     * 이메일과 비밀번호를 받아 로그인하고, 성공 시 JWT 토큰 발급
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. 이메일로 사용자 조회
        User user = userService.loginByEmail(request.getEmail());

        // 2. 사용자 존재 여부 확인
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자를 찾을 수 없습니다.");
        }

        // 3. 비밀번호 일치 여부 확인 (암호화된 비밀번호 비교)
        if (!passwordEncoder.matches(request.getPassword(), user.getUserPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }

        // 4. JWT 토큰 생성
        String token = jwtTokenProvider.createToken(user.getUserEmail(), user.getUserRole());

        // 5. 성공 응답 반환
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
   

}
