package com.ssafy.nomnom.model.dto.meal;

public class MealFood {
	private int mealFoodNo;
	private int mealNo;
	private String foodCode;
	private double foodAmount;
	public MealFood() {
	}
	public MealFood(int mealFoodNo, int mealNo, String foodCode, double foodAmount) {
		this.mealFoodNo = mealFoodNo;
		this.mealNo = mealNo;
		this.foodCode = foodCode;
		this.foodAmount = foodAmount;
	}
	public int getMealFoodNo() {
		return mealFoodNo;
	}
	public void setMealFoodNo(int mealFoodNo) {
		this.mealFoodNo = mealFoodNo;
	}
	public int getMealNo() {
		return mealNo;
	}
	public void setMealNo(int mealNo) {
		this.mealNo = mealNo;
	}
	public String getFoodCode() {
		return foodCode;
	}
	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}
	public double getFoodAmount() {
		return foodAmount;
	}
	public void setFoodAmount(double foodAmount) {
		this.foodAmount = foodAmount;
	}
	@Override
	public String toString() {
		return "MealFood [mealFoodNo=" + mealFoodNo + ", mealNo=" + mealNo + ", foodCode=" + foodCode + ", foodAmount="
				+ foodAmount + "]";
	}

	
}
