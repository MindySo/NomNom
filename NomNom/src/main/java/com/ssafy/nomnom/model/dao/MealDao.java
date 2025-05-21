package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.meal.Food;
import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.ReportDayResponse;
import com.ssafy.nomnom.model.dto.meal.ReportMonthlyResponse;
import com.ssafy.nomnom.model.dto.meal.ReportWeeklyResponse;

@Repository
public interface MealDao {
	
	//식단 조회(사용자별/일자)
	List<MealResponse> selectMealByUserAndRegDate(Meal meal);
	
	// 식단 조회(단일)
	List<MealFood> selectFoodByMealFoodNo(int mealNo);
	
	// 식단 등록
	int insertMeal(Meal meal);
	
	// 식단_음식 등록
	int insertMealFood(MealFood mealFood);

	// 음식 이름으로 음식 검색
	List<Food> selectFoodByFoodName(String foodname);
	
	// 음식 조회 (음식 이름)
	Food selectFoodByFoodCode(String foodCode);
	
	// 식단 수정(제목, 메모, 식사시간)
	int updateMeal(int mealNo);
	
	// 식단 삭제
	int deleteMeal(int mealNo);
	
	// 식단_음식 삭제(식단별)
	int deleteMealFoodByMealNo(int mealNo);
	
	// 식단_음식 삭제(단일)
	int deleteMealFoodByMealFoodNo(int mealFoodNo);
	
	// 수분 총합 조회(사용자별/일자)
	int selectWaterSumByUserAndRegDate(Meal meal);
	
	// 물 섭취 목록 조회(enum='WATER')(사용자별/일자)
	List<Meal> selectWaterListByUserAndRegDate(Meal meal);
	
	// 날짜별 모든 영양성분 총합 조회(영양분석) : reportDayResponse 객체
	List<ReportDayResponse> selectDayNutriSum(Meal meal);
	
	// 오늘 기준 일주일간 주요 영양성분 일별 총합 조회(최근 7일 주요 영양소 기록) : reportDayResponse List
	List<ReportDayResponse> selectWeeklyDayNutriAvg(int userNo);
	
	// 오늘 기준 일주일간 끼니(아침, 점심, 저녁)별 칼로리 평균(끼니별 칼로리 분석) : reportWeeklyResponse 객체
	ReportWeeklyResponse selectWeeklyMealTimeNutriAvg(int userNo);
	
	// 오늘 기준 월간 요일별 영양성분 평균 조회(요일별 영양소 섭취 평균) : reportDayResponse List
	ReportDayResponse selectMonthlyWeekdayNutriAvg(int userNo);
	
	// 오늘 기준 월간 끼니(아침, 점심, 저녁)별 횟수 조회(끼니별 식사 횟수) : reportMonthlyResponse 객체
	ReportMonthlyResponse selectMonthlyWeekdayCnt(int userNo);
	
	// 오늘 기준 월간 일 별 주요 영양성분별 평균 조회 : reportMonthlyResponse 객체
	ReportMonthlyResponse selectMonthlyDayNutriAvg(int userNo);
	
	
}
