package com.ssafy.nomnom.model.dto.meal;

public class MealFood {
	private Integer mealFoodNo;
	private Integer mealNo;
	private Integer foodNo;
	private String foodTitle;
	private String foodCategory;
	private Integer foodAmount;
	private Double calories;
	private Double carbohydrates;
	private Double protein;
	private Double fat;
	private Double saturatedFat;
	private Double unsaturatedFat;
	private Double cholesterol;
	private Double sodium;
	private Double sugar;
	private Double calcium;
	private Double vitamin;
	private Double iron;
	private Double mineral;

	public MealFood() {
	}

	public MealFood(Integer mealFoodNo, Integer mealNo, Integer foodNo, String foodTitle, String foodCategory,
			Integer foodAmount, Double calories, Double carbohydrates, Double protein, Double fat, Double saturatedFat,
			Double unsaturatedFat, Double cholesterol, Double sodium, Double sugar, Double calcium, Double vitamin,
			Double iron, Double mineral) {
		this.mealFoodNo = mealFoodNo;
		this.mealNo = mealNo;
		this.foodNo = foodNo;
		this.foodAmount = foodAmount;
		this.calories = calories;
		this.carbohydrates = carbohydrates;
		this.protein = protein;
		this.fat = fat;
		this.saturatedFat = saturatedFat;
		this.unsaturatedFat = unsaturatedFat;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.sugar = sugar;
		this.calcium = calcium;
		this.vitamin = vitamin;
		this.iron = iron;
		this.mineral = mineral;
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

	public String getFoodTitle() {
		return foodTitle;
	}

	public void setFoodTitle(String foodTitle) {
		this.foodTitle = foodTitle;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
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

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public Double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(Double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(Double saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public Double getUnsaturatedFat() {
		return unsaturatedFat;
	}

	public void setUnsaturatedFat(Double unsaturatedFat) {
		this.unsaturatedFat = unsaturatedFat;
	}

	public Double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Double getSodium() {
		return sodium;
	}

	public void setSodium(Double sodium) {
		this.sodium = sodium;
	}

	public Double getSugar() {
		return sugar;
	}

	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}

	public Double getCalcium() {
		return calcium;
	}

	public void setCalcium(Double calcium) {
		this.calcium = calcium;
	}

	public Double getVitamin() {
		return vitamin;
	}

	public void setVitamin(Double vitamin) {
		this.vitamin = vitamin;
	}

	public Double getIron() {
		return iron;
	}

	public void setIron(Double iron) {
		this.iron = iron;
	}

	public Double getMineral() {
		return mineral;
	}

	public void setMineral(Double mineral) {
		this.mineral = mineral;
	}

	@Override
	public String toString() {
		return "MealFood [mealFoodNo=" + mealFoodNo + ", mealNo=" + mealNo + ", foodNo=" + foodNo + ", foodTitle="
				+ foodTitle + ", foodCategory=" + foodCategory + ", foodAmount=" + foodAmount + ", calories="
				+ calories + ", carbohydrates=" + carbohydrates + ", protein=" + protein + ", fat=" + fat
				+ ", saturatedFat=" + saturatedFat + ", unsaturatedFat=" + unsaturatedFat + ", cholesterol="
				+ cholesterol + ", sodium=" + sodium + ", sugar=" + sugar + ", calcium=" + calcium + ", vitamin="
				+ vitamin + ", iron=" + iron + ", mineral=" + mineral + "]";
	}
}
