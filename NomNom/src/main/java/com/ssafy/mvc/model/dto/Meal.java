package com.ssafy.mvc.model.dto;

import java.time.LocalDate;

public class Meal {
	private Integer mealNo;
	private Integer userNo;
	private LocalDate mealRegDate;
	private String mealTime;
	
	
	public Meal() {
	}
	
	
	public Meal(Integer mealNo, Integer userNo, LocalDate mealRegDate, String mealTime) {
		this.mealNo = mealNo;
		this.userNo = userNo;
		this.mealRegDate = mealRegDate;
		this.mealTime = mealTime;
	}
	
	
	public Integer getMealNo() {
		return mealNo;
	}
	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public LocalDate getMealRegDate() {
		return mealRegDate;
	}
	public void setMealRegDate(LocalDate mealRegDate) {
		this.mealRegDate = mealRegDate;
	}
	public String getMealTime() {
		return mealTime;
	}
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	@Override
	public String toString() {
		return "Meal [mealNo=" + mealNo + ", userNo=" + userNo + ", mealRegDate=" + mealRegDate + ", mealTime="
				+ mealTime + "]";
	}
}
