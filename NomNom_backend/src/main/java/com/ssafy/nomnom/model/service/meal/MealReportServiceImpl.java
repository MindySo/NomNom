package com.ssafy.nomnom.model.service.meal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.nomnom.controller.UserController;
import com.ssafy.nomnom.model.dao.MealDao;
import com.ssafy.nomnom.model.dao.UserDao;
import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.dto.meal.NutriStandardResponse;
import com.ssafy.nomnom.model.dto.meal.ReportDayResponse;
import com.ssafy.nomnom.model.dto.meal.ReportMonthlyResponse;
import com.ssafy.nomnom.model.dto.meal.ReportWeeklyResponse;
import com.ssafy.nomnom.model.dto.user.User;

@Service
public class MealReportServiceImpl implements MealReportService{

    private final UserController userController;
	
	@Autowired
	private MealDao mealDao;

	@Autowired
	private UserDao userDao;


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

		monthlyResponse.setReportStartDate(LocalDate.now().minusDays(30));
		monthlyResponse.setReportEndDate(LocalDate.now());
		
		// 요일별 영양소 섭취 평균
		monthlyResponse.setWeekdayReportList(mealDao.selectMonthlyWeekdayNutriAvg(userNo));
		
		// 월 평균 영양성분 섭취량 평가
		ReportMonthlyResponse weekDayAvg = mealDao.selectMonthlyDayNutriAvg(userNo);
		monthlyResponse.setAvgMonthlycarbohydrate(weekDayAvg.getAvgMonthlycarbohydrate());
		monthlyResponse.setAvgMonthlyProtein(weekDayAvg.getAvgMonthlyProtein());
		monthlyResponse.setAvgMonthlyTotalFattyAcids(weekDayAvg.getAvgMonthlyTotalFattyAcids());
		monthlyResponse.setAvgMonthlySodium(weekDayAvg.getAvgMonthlySodium());
		monthlyResponse.setAvgMonthlySugar(weekDayAvg.getAvgMonthlySugar());
		
		User user = userDao.getUserByUserNo(userNo);
	    if (user == null) throw new IllegalArgumentException("사용자 없음");

	    int score = calculateMonthlyScore(userNo, monthlyResponse);
	    System.out.println(score);
	    monthlyResponse.setMonthlyScore(score);
		
		return monthlyResponse;
	}
	
	private int calculateMonthlyScore(int userNo, ReportMonthlyResponse response) {
	    NutriStandardResponse standard = getNutriStandard(userNo);
	    List<ReportDayResponse> days = response.getWeekdayReportList();

	    double avgEnergy = days.stream().mapToDouble(ReportDayResponse::getEnergy).average().orElse(0);

	    // 1. 적정 칼로리 유지 (30점)
	    double calorieStandard = standard.getEnergy();
	    double ratio = Math.abs(avgEnergy - calorieStandard) / calorieStandard;
	    double calorieScore = (ratio <= 0.25) ? 30 : (ratio <= 0.35 ? 20 : 10);

	    // 2. 영양소 균형 (40점)
	    double avgCarb = response.getAvgMonthlycarbohydrate();
	    double avgProtein = response.getAvgMonthlyProtein();
	    double avgFat = response.getAvgMonthlyTotalFattyAcids();

	    int matchCount = 0;
	    if (Math.abs(avgCarb - standard.getCarbohydrate()) / standard.getCarbohydrate() <= 0.25) matchCount++;
	    if (Math.abs(avgProtein - standard.getProtein()) / standard.getProtein() <= 0.25) matchCount++;
	    if (Math.abs(avgFat - standard.getTotalFattyAcids()) / standard.getTotalFattyAcids() <= 0.25) matchCount++;

	    double balanceScore = switch (matchCount) {
	        case 3 -> 40;
	        case 2 -> 30;
	        case 1 -> 20;
	        default -> 10;
	    };

	    // 3. 식사 규칙성 (30점)
	    int b = response.getBreakfastCount();
	    int l = response.getLunchCount();
	    int d = response.getDinnerCount();

	    double ruleScore = (b >= 15 && l >= 15 && d >= 15) ? 30
	                     : (b >= 7 && l >= 7 && d >= 7) ? 20 : 10;

	    System.out.println("calorie=" + calorieScore + " / balance=" + balanceScore + " / rule=" + ruleScore);
	    return (int)(calorieScore + balanceScore + ruleScore);
	}

	// 영양성분 기준치 계산
	//	칼로리: 남자 2500 / 여자 2000
	//	탄수화물: 칼로리 권장량의 60% / +- 15%
	//	단백질: 체중 X 0.9g / +-15%
	//	지방: 칼로리 권장량의 24% / +-15%
	//	수분: 남자 2500 여자 2000/ +- 20%
	//	나트륨: 2000 / +-20%
	//	당: 50g 이하
	@Override
	public NutriStandardResponse getNutriStandard(int userNo) {
	    // 사용자 정보를 불러온다고 가정
	    User user = userDao.getUserByUserNo(userNo);
	    if (user == null) {
	        throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
	    }

	    String gender = user.getUserGender() != null ? user.getUserGender() : "남자";
	    int weight = user.getUserWeight() != null ? user.getUserWeight() : 60; // 기본값 60kg
	    double calorie = gender.equalsIgnoreCase("남자") ? 2500 : 2000;

	    NutriStandardResponse response = new NutriStandardResponse();
	    response.setEnergy(roundTo1Decimal(calorie));
	    response.setCarbohydrate(roundTo1Decimal((calorie * 0.6) / 4)); // 탄수화물: 1g = 4kcal
	    response.setProtein(roundTo1Decimal(weight * 0.9));
	    response.setTotalFattyAcids(roundTo1Decimal((calorie * 0.24) / 9)); // 지방: 1g = 9kcal
	    response.setWater(roundTo1Decimal(gender.equalsIgnoreCase("남자") ? 2500 : 2000));
	    response.setSodium(roundTo1Decimal(2000));
	    response.setSugar(roundTo1Decimal(50)); // 이하 기준이지만, 최대값으로 설정

	    return response;
	}

	private double roundTo1Decimal(double value) {
	    return Math.round(value * 10) / 10.0;
	}


}
