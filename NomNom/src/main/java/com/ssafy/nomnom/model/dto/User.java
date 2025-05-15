package com.ssafy.nomnom.model.dto;

import java.time.LocalDate;

public class User {
	private Integer userNo;
    private String userId;
    private String userEmail;
    private String userPw;
    private LocalDate userRegDate;
    private LocalDate userBirthday;
    private String userGender;
    private String userRole;
    private Integer userHeight;
    private Integer userWeight;
    private Integer userPoint;
    
     
	public User() {
	}
	
	
	public User(Integer userNo, String userId, String userEmail, String userPw, LocalDate userRegDate,
			LocalDate userBirthday, String userGender, String userRole, Integer userHeight, Integer userWeight,
			Integer userPoint) {
		this.userNo = userNo;
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userRegDate = userRegDate;
		this.userBirthday = userBirthday;
		this.userGender = userGender;
		this.userRole = userRole;
		this.userHeight = userHeight;
		this.userWeight = userWeight;
		this.userPoint = userPoint;
	}
	
	
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
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
	public LocalDate getUserRegDate() {
		return userRegDate;
	}
	public void setUserRegDate(LocalDate userRegDate) {
		this.userRegDate = userRegDate;
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
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
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
	public Integer getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(Integer userPoint) {
		this.userPoint = userPoint;
	}
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userEmail=" + userEmail + ", userPw=" + userPw
				+ ", userRegDate=" + userRegDate + ", userBirthday=" + userBirthday + ", userGender=" + userGender
				+ ", userRole=" + userRole + ", userHeight=" + userHeight + ", userWeight=" + userWeight
				+ ", userPoint=" + userPoint + "]";
	}
}
