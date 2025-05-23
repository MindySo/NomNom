package com.ssafy.nomnom.controller.meal;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.NutriStandardResponse;
import com.ssafy.nomnom.model.dto.meal.ReportDayResponse;
import com.ssafy.nomnom.model.dto.meal.ReportMonthlyResponse;
import com.ssafy.nomnom.model.dto.meal.ReportWeeklyResponse;
import com.ssafy.nomnom.model.service.meal.MealReportService;
import com.ssafy.nomnom.model.service.meal.MealService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/meal/report")
@CrossOrigin("*")
@Tag(name = "영양 분석 API", description = "일간, 주간 월간 영양소 분석 제공")
public class MealReportController {
	
	private final MealService mealservice;
	private final MealReportService mealReportService;

	public MealReportController(MealService mealservice, MealReportService mealReportService) {
		this.mealservice = mealservice;
		this.mealReportService = mealReportService;
	}
	
	@Operation(summary = "일간 영양 분석")
	@GetMapping("daily")
	public ResponseEntity<ReportDayResponse> getDailyReport(@RequestParam int userNo
			, @Parameter(example = "2025-05-14") @RequestParam String mealRegDate){
		MealRequest meal = new MealRequest();
		meal.setUserNo(userNo);
		meal.setMealRegDate(LocalDate.parse(mealRegDate));
		ReportDayResponse reportDayResponse = mealReportService.getDayNutriSum(meal);
		System.out.println(reportDayResponse);
		
		return ResponseEntity.ok(reportDayResponse);
	}
	
	@Operation(summary = "주간 영양 분석")
	@GetMapping("weekly")
	public ResponseEntity<ReportWeeklyResponse> getWeeklyReport(@RequestParam int userNo){
		MealResponse meal = new MealResponse();
		meal.setUserNo(userNo);
		ReportWeeklyResponse weeklyResponse = mealReportService.getWeeklyReport(userNo);
		
		return ResponseEntity.ok(weeklyResponse);
	}
	
	@Operation(summary = "월간 영양 분석")
	@GetMapping("monthly")
	public ResponseEntity<ReportMonthlyResponse> getMonthlyReport(@RequestParam int userNo){
		MealResponse meal = new MealResponse();
		meal.setUserNo(userNo);
		ReportMonthlyResponse monthlyResponse = mealReportService.getMonthlyReport(userNo);
		
		return ResponseEntity.ok(monthlyResponse);
	}
	
	@Operation(summary = "사용자별 영양성분 권장 기준")
	@GetMapping("nutriStandard")
	public ResponseEntity<NutriStandardResponse> getNutriStandard(@RequestParam int userNo){
		NutriStandardResponse response = mealReportService.getNutriStandard(userNo);
		
		return ResponseEntity.ok(response);
	}
}
