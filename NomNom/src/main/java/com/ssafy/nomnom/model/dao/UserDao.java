package com.ssafy.nomnom.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.User;

@Repository
public interface UserDao {
	int insertUser(User user); //회원가입 
	User getUserByEmail(String email); //로그인 시 
	User getUserById(int UserId); //마이페이지 조회 
	int updateUser(User user); //회원정보 수정 
	int deleteUser(User user); //회원탈퇴 
	int insertDisease(@Param("userId") int userId, @Param("disease") String disease); //질병정보 입력 
    int insertAllergy(@Param("userId") int userId, @Param("allergy") String allergy); //알레르기정보 입력 
}

