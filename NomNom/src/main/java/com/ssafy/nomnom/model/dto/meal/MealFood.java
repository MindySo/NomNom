package com.ssafy.nomnom.model.dto.meal;

public class MealFood {
	private Integer mealFoodNo;
	private Integer mealNo;
	private String foodCode;
	private Integer foodAmount;

	public MealFood() {
	}

	public MealFood(Integer mealFoodNo, Integer mealNo, String foodCode, Integer foodAmount) {
		super();
		this.mealFoodNo = mealFoodNo;
		this.mealNo = mealNo;
		this.foodCode = foodCode;
		this.foodAmount = foodAmount;
	}

	public Integer getMealFoodNo() {
		return mealFoodNo;
	}

	public void setMealFoodNo(Integer mealFoodNo) {
		this.mealFoodNo = mealFoodNo;
	}

	public Integer getMealNo() {
		return mealNo;
	}

	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}

	public String getFoodCode() {
		return foodCode;
	}

	public void setFoodNo(String foodCode) {
		this.foodCode = foodCode;
	}

	public Integer getFoodAmount() {
		return foodAmount;
	}

	public void setFoodAmount(Integer foodAmount) {
		this.foodAmount = foodAmount;
	}

	@Override
	public String toString() {
		return "MealFood [mealFoodNo=" + mealFoodNo + ", mealNo=" + mealNo + ", foodCode=" + foodCode + ", foodAmount="
				+ foodAmount + "]";
	}

	
}
