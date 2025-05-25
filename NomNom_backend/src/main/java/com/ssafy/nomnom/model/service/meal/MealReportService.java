package com.ssafy.nomnom.model.service.meal;

import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.dto.meal.ReportDayResponse;
import com.ssafy.nomnom.model.dto.meal.ReportMonthlyResponse;
import com.ssafy.nomnom.model.dto.meal.ReportWeeklyResponse;
import com.ssafy.nomnom.model.dto.meal.NutriStandardResponse;

public interface MealReportService {
	

	// 선택 날짜의 : 영양분석
	ReportDayResponse getDayNutriSum(MealRequest meal);

	// 최근 7일 : 주간 리포트
	ReportWeeklyResponse getWeeklyReport(int userNo);
	
	// 최근 30일 : 월간 리포트
	ReportMonthlyResponse getMonthlyReport(int userNo);
	
	// 사용자별 영양소 기준
	NutriStandardResponse getNutriStandard(int userNo);
	
}
