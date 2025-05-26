package com.ssafy.nomnom.model.dto.meal;

public class Water {
	private String mealNo;
    private String mealTitle;
    private String foodAmount;
	public Water() {
	}
	public Water(String mealNo, String mealTitle, String foodAmount) {
		this.mealNo = mealNo;
		this.mealTitle = mealTitle;
		this.foodAmount = foodAmount;
	}
	public String getMealNo() {
		return mealNo;
	}
	public void setMealNo(String mealNo) {
		this.mealNo = mealNo;
	}
	public String getMealTitle() {
		return mealTitle;
	}
	public void setMealTitle(String mealTitle) {
		this.mealTitle = mealTitle;
	}
	public String getFoodAmount() {
		return foodAmount;
	}
	public void setFoodAmount(String foodAmount) {
		this.foodAmount = foodAmount;
	}
}
