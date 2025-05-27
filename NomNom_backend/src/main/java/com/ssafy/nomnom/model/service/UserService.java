package com.ssafy.nomnom.model.service;

import java.util.List;

import com.ssafy.nomnom.model.dto.Allergy;
import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.dto.user.UserRegisterRequest;
import com.ssafy.nomnom.model.dto.user.UserUpdateRequest;

public interface UserService {
	// 회원가입
	int registerUserWithAllergies(UserRegisterRequest request);

	// 이메일로 로그인
	User loginByEmail(String email);

	// 회원정보 수정
	int updateUserProfile(UserUpdateRequest request);

	// 회원탈퇴
	int deleteUserAccount(User user);

	// 소셜 로그인용 유저 저장 메서드
	int registerUser(User user);

	// 소셜 로그인 후 프로필 추가용
	int updateUser(User user);
	
	//알러지 조회 
	List<Allergy> getAllAllergies();
}
