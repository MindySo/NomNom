package com.ssafy.nomnom.model.dto.meal;

public class NutriStandardResponse {
	private double Energy;              // 칼로리 (Energy, kcal)
	private double Carbohydrate;        // 탄수화물
	private double Sugar;               // 당류
	private double Protein;             // 단백질
	private double TotalFattyAcids;     // 총 지방산
	private double Water;               // 수분
	private double Sodium;              // 나트륨
	
	public NutriStandardResponse() {
	}
	public NutriStandardResponse(double energy, double carbohydrate, double sugar, double protein,
			double totalFattyAcids, double water, double sodium) {
		super();
		Energy = energy;
		Carbohydrate = carbohydrate;
		Sugar = sugar;
		Protein = protein;
		TotalFattyAcids = totalFattyAcids;
		Water = water;
		Sodium = sodium;
	}
	public double getEnergy() {
		return Energy;
	}
	public void setEnergy(double energy) {
		Energy = energy;
	}
	public double getCarbohydrate() {
		return Carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		Carbohydrate = carbohydrate;
	}
	public double getSugar() {
		return Sugar;
	}
	public void setSugar(double sugar) {
		Sugar = sugar;
	}
	public double getProtein() {
		return Protein;
	}
	public void setProtein(double protein) {
		Protein = protein;
	}
	public double getTotalFattyAcids() {
		return TotalFattyAcids;
	}
	public void setTotalFattyAcids(double totalFattyAcids) {
		TotalFattyAcids = totalFattyAcids;
	}
	public double getWater() {
		return Water;
	}
	public void setWater(double water) {
		Water = water;
	}
	public double getSodium() {
		return Sodium;
	}
	public void setSodium(double sodium) {
		Sodium = sodium;
	}
	@Override
	public String toString() {
		return "UserDailyNutriValueResponse [Energy=" + Energy + ", Carbohydrate=" + Carbohydrate + ", Sugar=" + Sugar
				+ ", Protein=" + Protein + ", TotalFattyAcids=" + TotalFattyAcids + ", Water=" + Water + ", Sodium="
				+ Sodium + "]";
	}
	
	
}
