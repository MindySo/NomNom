package com.ssafy.nomnom.model.service;

import com.ssafy.nomnom.model.dto.user.User;
import com.ssafy.nomnom.model.dto.user.UserRegisterRequest;
import com.ssafy.nomnom.model.dto.user.UserUpdateRequest;

public interface UserService {
	//회원가입
    int registerUserWithAllergies(UserRegisterRequest request);
    
    //이메일로 로그인 
    User loginByEmail(String email); 
    
    //회원정보 수정 
    int updateUserProfile(UserUpdateRequest request);
    
    //회원탈퇴 
    int deleteUserAccount(User user); 
}

