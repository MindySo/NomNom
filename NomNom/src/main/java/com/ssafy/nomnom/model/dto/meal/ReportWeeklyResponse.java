package com.ssafy.nomnom.model.dto.meal;

import java.util.List;

public class ReportWeeklyResponse {
	List<ReportDayResponse> dailySumList;
	private double avgBreakfastEnergy;
	private double avgLunchEnergy;
	private double avgDinnerEnergy;
	private double avgWeeklyEnergy;
	private double avgWeeklycarbohydrate;
	private double avgWeeklyProtein;
	private double avgWeeklyTotalFattyAcids;
	private double avgWeeklyWater;
	
	public ReportWeeklyResponse() {
	}

	public ReportWeeklyResponse(List<ReportDayResponse> dailySumList, double avgBreakfastEnergy, double avgLunchEnergy,
			double avgDinnerEnergy, double avgWeeklyEnergy, double avgWeeklycarbohydrate, double avgWeeklyProtein,
			double avgWeeklyTotalFattyAcids, double avgWeeklyWater) {
		super();
		this.dailySumList = dailySumList;
		this.avgBreakfastEnergy = avgBreakfastEnergy;
		this.avgLunchEnergy = avgLunchEnergy;
		this.avgDinnerEnergy = avgDinnerEnergy;
		this.avgWeeklyEnergy = avgWeeklyEnergy;
		this.avgWeeklycarbohydrate = avgWeeklycarbohydrate;
		this.avgWeeklyProtein = avgWeeklyProtein;
		this.avgWeeklyTotalFattyAcids = avgWeeklyTotalFattyAcids;
		this.avgWeeklyWater = avgWeeklyWater;
	}

	public List<ReportDayResponse> getDailySumList() {
		return dailySumList;
	}

	public void setDailySumList(List<ReportDayResponse> dailySumList) {
		this.dailySumList = dailySumList;
	}

	public double getAvgBreakfastEnergy() {
		return avgBreakfastEnergy;
	}

	public void setAvgBreakfastEnergy(double avgBreakfastEnergy) {
		this.avgBreakfastEnergy = avgBreakfastEnergy;
	}

	public double getAvgLunchEnergy() {
		return avgLunchEnergy;
	}

	public void setAvgLunchEnergy(double avgLunchEnergy) {
		this.avgLunchEnergy = avgLunchEnergy;
	}

	public double getAvgDinnerEnergy() {
		return avgDinnerEnergy;
	}

	public void setAvgDinnerEnergy(double avgDinnerEnergy) {
		this.avgDinnerEnergy = avgDinnerEnergy;
	}

	public double getAvgWeeklyEnergy() {
		return avgWeeklyEnergy;
	}

	public void setAvgWeeklyEnergy(double avgWeeklyEnergy) {
		this.avgWeeklyEnergy = avgWeeklyEnergy;
	}

	public double getAvgWeeklycarbohydrate() {
		return avgWeeklycarbohydrate;
	}

	public void setAvgWeeklycarbohydrate(double avgWeeklycarbohydrate) {
		this.avgWeeklycarbohydrate = avgWeeklycarbohydrate;
	}

	public double getAvgWeeklyProtein() {
		return avgWeeklyProtein;
	}

	public void setAvgWeeklyProtein(double avgWeeklyProtein) {
		this.avgWeeklyProtein = avgWeeklyProtein;
	}

	public double getAvgWeeklyTotalFattyAcids() {
		return avgWeeklyTotalFattyAcids;
	}

	public void setAvgWeeklyTotalFattyAcids(double avgWeeklyTotalFattyAcids) {
		this.avgWeeklyTotalFattyAcids = avgWeeklyTotalFattyAcids;
	}

	public double getAvgWeeklyWater() {
		return avgWeeklyWater;
	}

	public void setAvgWeeklyWater(double avgWeeklyWater) {
		this.avgWeeklyWater = avgWeeklyWater;
	}

	@Override
	public String toString() {
		return "ReportWeeklyResponse [dailySumList=" + dailySumList + ", avgBreakfastEnergy=" + avgBreakfastEnergy
				+ ", avgLunchEnergy=" + avgLunchEnergy + ", avgDinnerEnergy=" + avgDinnerEnergy + ", avgWeeklyEnergy="
				+ avgWeeklyEnergy + ", avgWeeklycarbohydrate=" + avgWeeklycarbohydrate + ", avgWeeklyProtein="
				+ avgWeeklyProtein + ", avgWeeklyTotalFattyAcids=" + avgWeeklyTotalFattyAcids + ", avgWeeklyWater="
				+ avgWeeklyWater + "]";
	}
	
	
}
