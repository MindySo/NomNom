package com.ssafy.nomnom.model.dto.meal;

import java.time.LocalDate;

public class Meal {
	private int mealNo;
	private int userNo;
	private LocalDate mealRegDate;
	private MealTimeEnum mealTime;
	private String mealTitle;
	
	public Meal() {
	}
	public Meal(int mealNo, int userNo, LocalDate mealRegDate, MealTimeEnum mealTime, String mealTitle) {
		super();
		this.mealNo = mealNo;
		this.userNo = userNo;
		this.mealRegDate = mealRegDate;
		this.mealTime = mealTime;
		this.mealTitle = mealTitle;
	}
	public int getMealNo() {
		return mealNo;
	}
	public void setMealNo(int mealNo) {
		this.mealNo = mealNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public LocalDate getMealRegDate() {
		return mealRegDate;
	}
	public void setMealRegDate(LocalDate mealRegDate) {
		this.mealRegDate = mealRegDate;
	}
	public MealTimeEnum getMealTime() {
		return mealTime;
	}
	public void setMealTime(MealTimeEnum mealTime) {
		this.mealTime = mealTime;
	}
	public String getMealTitle() {
		return mealTitle;
	}
	public void setMealTitle(String mealTitle) {
		this.mealTitle = mealTitle;
	}
	@Override
	public String toString() {
		return "Meal [mealNo=" + mealNo + ", userNo=" + userNo + ", mealRegDate=" + mealRegDate + ", mealTime="
				+ mealTime + ", mealTitle=" + mealTitle + "]";
	}
	
	
}
