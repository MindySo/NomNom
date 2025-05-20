package com.ssafy.nomnom.model.dto.meal;

import java.util.List;

public class ReportWeeklyResponse {
	List<ReportDayResponse> dailyList;
	private double avgBreakfastEnergy;
	private double avgLunchEnergy;
	private double avgDinnerEnergy;
	private double avgWeeklyEnergy;
	private double avgWeeklycarbohydrate;
	private double avgWeeklyProtein;
	private double avgWeeklyTotalFattyAcids;
	private double avgWeeklyWater;
}
