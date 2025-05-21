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
	
	public ReportMonthlyResponse() {
	}
	public ReportMonthlyResponse(LocalDate reportStartDate, LocalDate reportEndDate,
			List<ReportDayResponse> weekdayReportList, int monthlyScore, int breakfastCount, int lunchCount,
			int dinnerCount, double avgMonthlycarbohydrate, double avgMonthlyProtein, double avgMonthlyTotalFattyAcids,
			double avgMonthlySodium, double avgMonthlySugar) {
		super();
		this.reportStartDate = reportStartDate;
		this.reportEndDate = reportEndDate;
		this.weekdayReportList = weekdayReportList;
		this.monthlyScore = monthlyScore;
		this.breakfastCount = breakfastCount;
		this.lunchCount = lunchCount;
		this.dinnerCount = dinnerCount;
		this.avgMonthlycarbohydrate = avgMonthlycarbohydrate;
		this.avgMonthlyProtein = avgMonthlyProtein;
		this.avgMonthlyTotalFattyAcids = avgMonthlyTotalFattyAcids;
		this.avgMonthlySodium = avgMonthlySodium;
		this.avgMonthlySugar = avgMonthlySugar;
	}
	public LocalDate getReportStartDate() {
		return reportStartDate;
	}
	public void setReportStartDate(LocalDate reportStartDate) {
		this.reportStartDate = reportStartDate;
	}
	public LocalDate getReportEndDate() {
		return reportEndDate;
	}
	public void setReportEndDate(LocalDate reportEndDate) {
		this.reportEndDate = reportEndDate;
	}
	public List<ReportDayResponse> getWeekdayReportList() {
		return weekdayReportList;
	}
	public void setWeekdayReportList(List<ReportDayResponse> weekdayReportList) {
		this.weekdayReportList = weekdayReportList;
	}
	public int getMonthlyScore() {
		return monthlyScore;
	}
	public void setMonthlyScore(int monthlyScore) {
		this.monthlyScore = monthlyScore;
	}
	public int getBreakfastCount() {
		return breakfastCount;
	}
	public void setBreakfastCount(int breakfastCount) {
		this.breakfastCount = breakfastCount;
	}
	public int getLunchCount() {
		return lunchCount;
	}
	public void setLunchCount(int lunchCount) {
		this.lunchCount = lunchCount;
	}
	public int getDinnerCount() {
		return dinnerCount;
	}
	public void setDinnerCount(int dinnerCount) {
		this.dinnerCount = dinnerCount;
	}
	public double getAvgMonthlycarbohydrate() {
		return avgMonthlycarbohydrate;
	}
	public void setAvgMonthlycarbohydrate(double avgMonthlycarbohydrate) {
		this.avgMonthlycarbohydrate = avgMonthlycarbohydrate;
	}
	public double getAvgMonthlyProtein() {
		return avgMonthlyProtein;
	}
	public void setAvgMonthlyProtein(double avgMonthlyProtein) {
		this.avgMonthlyProtein = avgMonthlyProtein;
	}
	public double getAvgMonthlyTotalFattyAcids() {
		return avgMonthlyTotalFattyAcids;
	}
	public void setAvgMonthlyTotalFattyAcids(double avgMonthlyTotalFattyAcids) {
		this.avgMonthlyTotalFattyAcids = avgMonthlyTotalFattyAcids;
	}
	public double getAvgMonthlySodium() {
		return avgMonthlySodium;
	}
	public void setAvgMonthlySodium(double avgMonthlySodium) {
		this.avgMonthlySodium = avgMonthlySodium;
	}
	public double getAvgMonthlySugar() {
		return avgMonthlySugar;
	}
	public void setAvgMonthlySugar(double avgMonthlySugar) {
		this.avgMonthlySugar = avgMonthlySugar;
	}
	@Override
	public String toString() {
		return "ReportMonthlyResponse [reportStartDate=" + reportStartDate + ", reportEndDate=" + reportEndDate
				+ ", weekdayReportList=" + weekdayReportList + ", monthlyScore=" + monthlyScore + ", breakfastCount="
				+ breakfastCount + ", lunchCount=" + lunchCount + ", dinnerCount=" + dinnerCount
				+ ", avgMonthlycarbohydrate=" + avgMonthlycarbohydrate + ", avgMonthlyProtein=" + avgMonthlyProtein
				+ ", avgMonthlyTotalFattyAcids=" + avgMonthlyTotalFattyAcids + ", avgMonthlySodium=" + avgMonthlySodium
				+ ", avgMonthlySugar=" + avgMonthlySugar + "]";
	}
	
	
	
}
