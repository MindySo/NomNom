package com.ssafy.nomnom.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.user.User;

@Repository
public interface UserDao {
	int insertUser(User user); //회원가입 
	User getUserByEmail(String email); //로그인
	int updateUser(User user); //회원정보 수정 
	int deleteUser(User user); //회원탈퇴 

	int insertUserAllergy(@Param("userNo") int userNo, @Param("allergyNo") int allergyNo); //알러지 추가 
    int deleteUserAllergies(int userNo); //알러지 전체 삭제
    Integer getAllergyIdByName(String allergyName); //알러지 이름으로 조회 
    
	User getUserByUserNo(int userNo);	// userNo로 user 정보 불러오기
	
}

