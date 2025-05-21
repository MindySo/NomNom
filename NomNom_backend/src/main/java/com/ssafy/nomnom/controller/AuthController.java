package com.ssafy.nomnom.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nomnom.config.JwtTokenProvider;
import com.ssafy.nomnom.model.dto.user.LoginRequest;
import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.service.UserService;

@RestController
@RequestMapping("/api/auth")
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

	// 로그인 API: POST /api/auth/login
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		User user = userService.loginByEmail(request.getEmail());

		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자를 찾을 수 없습니다.");
		}

		if (!passwordEncoder.matches(request.getPassword(), user.getUserPw())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
		}

		String token = jwtTokenProvider.createToken(user.getUserEmail(), user.getUserRole());

		return ResponseEntity.ok(Collections.singletonMap("token", token));
	}

}
