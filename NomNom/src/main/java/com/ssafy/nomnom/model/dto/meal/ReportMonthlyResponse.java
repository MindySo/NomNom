package com.ssafy.nomnom.model.dto.meal;

import java.time.LocalDate;
import java.util.List;

public class ReportMonthlyResponse {
	private LocalDate reportStartDate;
	private LocalDate reportEndDate;
	private List<ReportDayResponse> weekdayReportList;
	private int monthlyScore;
	private int breakfastCount;
    private int lunchCount;
    private int dinnerCount;
	private double avgMonthlycarbohydrate;
	private double avgMonthlyProtein;
	private double avgMonthlyTotalFattyAcids;
	private double avgMonthlySodium;
	private double avgMonthlySugar;
	
	
}
