package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.meal.Food;
import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.ReportDayResponse;
import com.ssafy.nomnom.model.dto.meal.ReportMonthlyResponse;
import com.ssafy.nomnom.model.dto.meal.ReportWeeklyResponse;

@Repository
public interface MealDao {

	////// Meal(식단, 음식 CRUD) //////

	// 식단 조회(사용자별/일자)
	List<MealResponse> selectMealByUserAndRegDate(MealRequest meal);

	// 식단 및 식단에 포함된 영양소 총합 조회(단일)
	MealResponse selectMealAndNutriSumByMealNo(int mealNo);

	// 식단에 포함된 음식들 조회(단일)
	List<Food> selectFoodsByMealNo(int mealNo);

	// 식단 등록
	int insertMeal(MealRequest meal);

	// 식단_음식 등록
	int insertMealFood(MealFood mealFood);

	// 음식 이름으로 음식 검색
	List<Food> selectFoodByFoodName(String foodname);

	// 음식 조회 (음식 이름)
	Food selectFoodByFoodCode(String foodCode);

	// 식단 수정(제목, 메모, 식사시간)
	int updateMeal(Meal meal);

	// 식단 삭제
	int deleteMeal(int mealNo);

	// 식단_음식 삭제(식단별)
	int deleteMealFoodByMealNo(int mealNo);

	// 식단_음식 삭제(단일)
	int deleteMealFoodByMealFoodNo(int mealFoodNo);

	// 물 섭취 목록 조회(enum='WATER')(사용자별/일자)
	List<MealResponse> selectWaterListByUserAndRegDate(MealRequest meal);

	////// MealReport(리포트) //////

	// 날짜별 모든 영양성분 총합 조회(영양분석) : reportDayResponse 객체
	ReportDayResponse selectDayNutriSum(MealRequest meal);

	// 오늘 기준 일주일간 주요 영양성분 일별 총합 조회(최근 7일 주요 영양소 기록) : reportDayResponse List
	List<ReportDayResponse> selectWeeklyDayNutriSum(int userNo);

	// 오늘 기준 일주일간 끼니(아침, 점심, 저녁)별 칼로리 평균(끼니별 칼로리 분석) : reportWeeklyResponse 객체
	ReportWeeklyResponse selectWeeklyMealTimeNutriAvg(int userNo);
	
	//오늘 기준 일주일간 영양성분별 일평균 조회(주간 영양소 평균) : reportDayResponse
	ReportDayResponse selectWeeklyNutriAvg(int userNo);

	// 오늘 기준 월간 요일별 영양성분 평균 조회(요일별 영양소 섭취 평균) : reportDayResponse List
	List<ReportDayResponse> selectMonthlyWeekdayNutriAvg(int userNo);

	// 오늘 기준 월간 끼니(아침, 점심, 저녁)별 횟수 조회(끼니별 식사 횟수) : reportMonthlyResponse 객체
	ReportMonthlyResponse selectMonthlyWeekdayCnt(int userNo);

	// 오늘 기준 월간 일 별 주요 영양성분별 평균 조회(월 평균 영양성분 섭취량 평가) : reportMonthlyResponse 객체
	ReportMonthlyResponse selectMonthlyDayNutriAvg(int userNo);

}
