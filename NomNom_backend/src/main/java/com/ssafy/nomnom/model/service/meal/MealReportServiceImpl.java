package com.ssafy.nomnom.model.service.meal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.nomnom.controller.UserController;
import com.ssafy.nomnom.model.dao.MealDao;
import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.ReportDayResponse;
import com.ssafy.nomnom.model.dto.meal.ReportMonthlyResponse;
import com.ssafy.nomnom.model.dto.meal.ReportWeeklyResponse;

@Service
public class MealReportServiceImpl implements MealReportService{

    private final UserController userController;
	
	@Autowired
	private MealDao mealDao;


    MealReportServiceImpl(UserController userController) {
        this.userController = userController;
    }
	

	@Override
	public ReportDayResponse getDayNutriSum(MealRequest meal) {
		return mealDao.selectDayNutriSum(meal);
	}

	@Override
	public ReportWeeklyResponse getWeeklyReport(int userNo) {
		ReportWeeklyResponse weeklyResponse = new ReportWeeklyResponse();
	
		// 끼니별 칼로리 분석
		ReportWeeklyResponse mealTimeNutriAvg = mealDao.selectWeeklyMealTimeNutriAvg(userNo);
		System.out.println(mealTimeNutriAvg);
		if(mealTimeNutriAvg != null) {
			weeklyResponse.setAvgBreakfastEnergy(mealTimeNutriAvg.getAvgBreakfastEnergy());
			weeklyResponse.setAvgLunchEnergy(mealTimeNutriAvg.getAvgLunchEnergy());
			weeklyResponse.setAvgDinnerEnergy(mealTimeNutriAvg.getAvgDinnerEnergy());
		}
		
		// 최근 7일 주요 영양소 기록
		weeklyResponse.setDailySumList(mealDao.selectWeeklyDayNutriSum(userNo));
		
		// 끼니별 칼로리 분석
		ReportDayResponse weeklyNutriAvg = mealDao.selectWeeklyNutriAvg(userNo);
		System.out.println(weeklyNutriAvg);
		weeklyResponse.setAvgWeeklyEnergy(weeklyNutriAvg.getEnergy());
		weeklyResponse.setAvgWeeklycarbohydrate(weeklyNutriAvg.getCarbohydrate());
		weeklyResponse.setAvgWeeklyProtein(weeklyNutriAvg.getProtein());
		weeklyResponse.setAvgWeeklyTotalFattyAcids(weeklyNutriAvg.getTotalFattyAcids());
		weeklyResponse.setAvgWeeklyWater(weeklyNutriAvg.getWater());
		
		return weeklyResponse;
	}

	@Override
	public ReportMonthlyResponse getMonthlyReport(int userNo) {
		// 끼니별 식사 횟수
		ReportMonthlyResponse monthlyResponse = mealDao.selectMonthlyWeekdayCnt(userNo);

		monthlyResponse.setReportStartDate(LocalDate.now());
		monthlyResponse.setReportEndDate(LocalDate.now().minusDays(7));
		
		// 요일별 영양소 섭취 평균
		LocalDate date = LocalDate.now();
		List<ReportDayResponse> list = mealDao.selectMonthlyWeekdayNutriAvg(userNo);
		for(ReportDayResponse weekday : list) {
			weekday.setReportDate(date);
			date = date.plusDays(1);
		}
		monthlyResponse.setWeekdayReportList(list);
			
		
		// 월 평균 영양성분 섭취량 평가
		ReportMonthlyResponse weekDayAvg = mealDao.selectMonthlyDayNutriAvg(userNo);
		
		return monthlyResponse;
	}
}
