package com.ssafy.nomnom.model.dto.meal;

public class MealResponse {
	private Integer mealNo;
	private String mealTime;
	private String mealTitle;
	private Double calSum;
	private Double carbSum;
	private Double proSum;
	private Double fatSum;
	private Double waterSum;
	
	
	public MealResponse() {
	}
	
	public MealResponse(Integer mealNo, String mealTime, String mealTitle, Double calSum, Double carbSum, Double proSum,
			Double fatSum, Double waterSum) {
		this.mealNo = mealNo;
		this.mealTime = mealTime;
		this.mealTitle = mealTitle;
		this.calSum = calSum;
		this.carbSum = carbSum;
		this.proSum = proSum;
		this.fatSum = fatSum;
		this.waterSum = waterSum;
	}

	public Integer getMealNo() {
		return mealNo;
	}

	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}

	public String getMealTime() {
		return mealTime;
	}

	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}

	public String getMealTitle() {
		return mealTitle;
	}

	public void setMealTitle(String mealTitle) {
		this.mealTitle = mealTitle;
	}

	public Double getCalSum() {
		return calSum;
	}

	public void setCalSum(Double calSum) {
		this.calSum = calSum;
	}

	public Double getCarbSum() {
		return carbSum;
	}

	public void setCarbSum(Double carbSum) {
		this.carbSum = carbSum;
	}

	public Double getProSum() {
		return proSum;
	}

	public void setProSum(Double proSum) {
		this.proSum = proSum;
	}

	public Double getFatSum() {
		return fatSum;
	}

	public void setFatSum(Double fatSum) {
		this.fatSum = fatSum;
	}

	public Double getWaterSum() {
		return waterSum;
	}

	public void setWaterSum(Double waterSum) {
		this.waterSum = waterSum;
	}

	@Override
	public String toString() {
		return "MealRequest [mealNo=" + mealNo + ", mealTime=" + mealTime + ", mealTitle=" + mealTitle + ", calSum="
				+ calSum + ", carbSum=" + carbSum + ", proSum=" + proSum + ", fatSum=" + fatSum + ", waterSum="
				+ waterSum + "]";
	}
	
	
}
