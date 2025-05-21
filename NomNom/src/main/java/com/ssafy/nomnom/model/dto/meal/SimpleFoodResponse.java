package com.ssafy.nomnom.model.dto.meal;

public class SimpleFoodResponse {
	private String foodCode;
    private String foodName;
    private String foodUpperGroup;
    private String foodSubGroup;
    private double foodWeight;
	public SimpleFoodResponse() {
	}
	public SimpleFoodResponse(String foodCode, String foodName, String foodUpperGroup, String foodSubGroup,
			double foodWeight) {
		this.foodCode = foodCode;
		this.foodName = foodName;
		this.foodUpperGroup = foodUpperGroup;
		this.foodSubGroup = foodSubGroup;
		this.foodWeight = foodWeight;
	}
	public String getFoodCode() {
		return foodCode;
	}
	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodUpperGroup() {
		return foodUpperGroup;
	}
	public void setFoodUpperGroup(String foodUpperGroup) {
		this.foodUpperGroup = foodUpperGroup;
	}
	public String getFoodSubGroup() {
		return foodSubGroup;
	}
	public void setFoodSubGroup(String foodSubGroup) {
		this.foodSubGroup = foodSubGroup;
	}
	public double getFoodWeight() {
		return foodWeight;
	}
	public void setFoodWeight(double foodWeight) {
		this.foodWeight = foodWeight;
	}
	@Override
	public String toString() {
		return "SimpleFoodResponse [foodCode=" + foodCode + ", foodName=" + foodName + ", foodUpperGroup="
				+ foodUpperGroup + ", foodSubGroup=" + foodSubGroup + ", foodWeight=" + foodWeight + "]";
	}
    
}
