package com.ssafy.nomnom.model.dto.user;


import java.time.LocalDate;
import java.util.List;

public class UserRegisterRequest {
    private String userId;
    private String userEmail;
    private String userPw;
    private LocalDate userBirthday;
    private String userGender;
    private Integer userHeight;
    private Integer userWeight;

    private List<Integer> allergyIds; // 선택한 allergy_id 목록

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public LocalDate getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(LocalDate userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Integer getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(Integer userHeight) {
		this.userHeight = userHeight;
	}

	public Integer getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(Integer userWeight) {
		this.userWeight = userWeight;
	}

	public List<Integer> getAllergyIds() {
		return allergyIds;
	}

	public void setAllergyIds(List<Integer> allergyIds) {
		this.allergyIds = allergyIds;
	}

    
}