package com.ssafy.nomnom.model.dto.meal;

public class MealFood {
	private Integer mealFoodNo;
	private Integer mealNo;
	private Integer foodNo;
	private Integer foodAmount;

	public MealFood() {
	}

	public MealFood(Integer mealFoodNo, Integer mealNo, Integer foodNo, Integer foodAmount) {
		super();
		this.mealFoodNo = mealFoodNo;
		this.mealNo = mealNo;
		this.foodNo = foodNo;
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

	public Integer getFoodNo() {
		return foodNo;
	}

	public void setFoodNo(Integer foodNo) {
		this.foodNo = foodNo;
	}

	public Integer getFoodAmount() {
		return foodAmount;
	}

	public void setFoodAmount(Integer foodAmount) {
		this.foodAmount = foodAmount;
	}

	@Override
	public String toString() {
		return "MealFood [mealFoodNo=" + mealFoodNo + ", mealNo=" + mealNo + ", foodNo=" + foodNo + ", foodAmount="
				+ foodAmount + "]";
	}

	
}
