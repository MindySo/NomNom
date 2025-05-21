package com.ssafy.nomnom.model.dto.meal;

import java.time.LocalDate;

public class ReportDayResponse {
	private LocalDate reportDate;		// 조회 날짜
	private String reportWeekday;		// 조회 요일
	private double energy;              // 칼로리 (Energy, kcal)
	private double carbohydrate;        // 탄수화물
	private double sugar;               // 당류
	private double dietaryFiber;        // 식이섬유
	private double protein;             // 단백질
	private double ash;                 // 회분
	private double totalFattyAcids;     // 총 지방산
	private double saturatedFats;       // 포화지방산
	private double unsaturatedFats;     // 단일불포화지방산 + 다중불포화지방산
	private double water;               // 수분
	private double vitaminACarotene;    // 비타민 A (총 카로틴계)
	private double vitaminB1;           // 비타민 B1 (Thiamine)
	private double vitaminB2;           // 비타민 B2 (Riboflavin)
	private double niacin;              // 비타민 B3 나이아신
	private double folate;              // 엽산 (Folate)
	private double vitaminC;            // 비타민 C
	private double vitaminD;            // 비타민 D
	private double cholesterol;         // 콜레스테롤
	private double sodium;              // 나트륨
	private double calcium;             // 칼슘
	private double iron;                // 철
	private double phosphorus;          // 인
	private double potassium;           // 칼륨
	public ReportDayResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportDayResponse(LocalDate reportDate, String reportWeekday, double energy, double carbohydrate,
			double sugar, double dietaryFiber, double protein, double ash, double totalFattyAcids, double saturatedFats,
			double unsaturatedFats, double water, double vitaminACarotene, double vitaminB1, double vitaminB2,
			double niacin, double folate, double vitaminC, double vitaminD, double cholesterol, double sodium,
			double calcium, double iron, double phosphorus, double potassium) {
		super();
		this.reportDate = reportDate;
		this.reportWeekday = reportWeekday;
		this.energy = energy;
		this.carbohydrate = carbohydrate;
		this.sugar = sugar;
		this.dietaryFiber = dietaryFiber;
		this.protein = protein;
		this.ash = ash;
		this.totalFattyAcids = totalFattyAcids;
		this.saturatedFats = saturatedFats;
		this.unsaturatedFats = unsaturatedFats;
		this.water = water;
		this.vitaminACarotene = vitaminACarotene;
		this.vitaminB1 = vitaminB1;
		this.vitaminB2 = vitaminB2;
		this.niacin = niacin;
		this.folate = folate;
		this.vitaminC = vitaminC;
		this.vitaminD = vitaminD;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.calcium = calcium;
		this.iron = iron;
		this.phosphorus = phosphorus;
		this.potassium = potassium;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportWeekday() {
		return reportWeekday;
	}
	public void setReportWeekday(String reportWeekday) {
		this.reportWeekday = reportWeekday;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public double getDietaryFiber() {
		return dietaryFiber;
	}
	public void setDietaryFiber(double dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getAsh() {
		return ash;
	}
	public void setAsh(double ash) {
		this.ash = ash;
	}
	public double getTotalFattyAcids() {
		return totalFattyAcids;
	}
	public void setTotalFattyAcids(double totalFattyAcids) {
		this.totalFattyAcids = totalFattyAcids;
	}
	public double getSaturatedFats() {
		return saturatedFats;
	}
	public void setSaturatedFats(double saturatedFats) {
		this.saturatedFats = saturatedFats;
	}
	public double getUnsaturatedFats() {
		return unsaturatedFats;
	}
	public void setUnsaturatedFats(double unsaturatedFats) {
		this.unsaturatedFats = unsaturatedFats;
	}
	public double getWater() {
		return water;
	}
	public void setWater(double water) {
		this.water = water;
	}
	public double getVitaminACarotene() {
		return vitaminACarotene;
	}
	public void setVitaminACarotene(double vitaminACarotene) {
		this.vitaminACarotene = vitaminACarotene;
	}
	public double getVitaminB1() {
		return vitaminB1;
	}
	public void setVitaminB1(double vitaminB1) {
		this.vitaminB1 = vitaminB1;
	}
	public double getVitaminB2() {
		return vitaminB2;
	}
	public void setVitaminB2(double vitaminB2) {
		this.vitaminB2 = vitaminB2;
	}
	public double getNiacin() {
		return niacin;
	}
	public void setNiacin(double niacin) {
		this.niacin = niacin;
	}
	public double getFolate() {
		return folate;
	}
	public void setFolate(double folate) {
		this.folate = folate;
	}
	public double getVitaminC() {
		return vitaminC;
	}
	public void setVitaminC(double vitaminC) {
		this.vitaminC = vitaminC;
	}
	public double getVitaminD() {
		return vitaminD;
	}
	public void setVitaminD(double vitaminD) {
		this.vitaminD = vitaminD;
	}
	public double getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}
	public double getSodium() {
		return sodium;
	}
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	public double getCalcium() {
		return calcium;
	}
	public void setCalcium(double calcium) {
		this.calcium = calcium;
	}
	public double getIron() {
		return iron;
	}
	public void setIron(double iron) {
		this.iron = iron;
	}
	public double getPhosphorus() {
		return phosphorus;
	}
	public void setPhosphorus(double phosphorus) {
		this.phosphorus = phosphorus;
	}
	public double getPotassium() {
		return potassium;
	}
	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}
	@Override
	public String toString() {
		return "ReportDayResponse [reportDate=" + reportDate + ", reportWeekday=" + reportWeekday + ", energy=" + energy
				+ ", carbohydrate=" + carbohydrate + ", sugar=" + sugar + ", dietaryFiber=" + dietaryFiber
				+ ", protein=" + protein + ", ash=" + ash + ", totalFattyAcids=" + totalFattyAcids + ", saturatedFats="
				+ saturatedFats + ", unsaturatedFats=" + unsaturatedFats + ", water=" + water + ", vitaminACarotene="
				+ vitaminACarotene + ", vitaminB1=" + vitaminB1 + ", vitaminB2=" + vitaminB2 + ", niacin=" + niacin
				+ ", folate=" + folate + ", vitaminC=" + vitaminC + ", vitaminD=" + vitaminD + ", cholesterol="
				+ cholesterol + ", sodium=" + sodium + ", calcium=" + calcium + ", iron=" + iron + ", phosphorus="
				+ phosphorus + ", potassium=" + potassium + "]";
	}
	
	

}
