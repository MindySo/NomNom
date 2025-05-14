package com.ssafy.nomnom.model.dto.meal;

import java.time.LocalDate;

public class MealResponse {
	private Integer mealNo;
	private String mealTime;
	private String mealTitle;
	private Double calSum;
	private Double carbSum;
	private Double proSum;
	private Double fatSum;
	
	
	public MealResponse() {
	}
	
	
	public MealResponse(Integer mealNo, String mealTime, String mealTitle, Double calSum, Double carbSum, Double proSum,
			Double fatSum) {
		super();
		this.mealNo = mealNo;
		this.mealTime = mealTime;
		this.mealTitle = mealTitle;
		this.calSum = calSum;
		this.carbSum = carbSum;
		this.proSum = proSum;
		this.fatSum = fatSum;
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
	public Double getcalSum() {
		return calSum;
	}
	public void setcalSum(Double calSum) {
		this.calSum = calSum;
	}
	public Double getcarbSum() {
		return carbSum;
	}
	public void setcarbSum(Double carbSum) {
		this.carbSum = carbSum;
	}
	public Double getproSum() {
		return proSum;
	}
	public void setproSum(Double proSum) {
		this.proSum = proSum;
	}
	public Double getfatSum() {
		return fatSum;
	}
	public void setfatSum(Double fatSum) {
		this.fatSum = fatSum;
	}
	@Override
	public String toString() {
		return "MealResponse [mealNo=" + mealNo + ", mealTime="	+ mealTime + ", mealTitle=" + mealTitle + 
				", calSum=" + calSum + ", carbSum=" + carbSum + ", proSum=" + proSum + ", fatSum=" + fatSum + "]";
	}
}
