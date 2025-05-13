package com.ssafy.mvc.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.mvc.model.dto.Meal;
import com.ssafy.mvc.model.dto.MealFood;
import com.ssafy.mvc.model.dto.MealResponse;

@Repository
public interface MealDao {
	List<MealResponse> selectMealByUserAndRegDate(Meal meal) throws SQLException;
	List<MealFood> selectFoodByMealFoodNo(int mealNo) throws SQLException;
	int insertMeal(Meal meal) throws SQLException;
	int insertMealFood(MealFood mealFood) throws SQLException;
	int deleteMeal(int mealNo) throws SQLException;
	int deleteMealFood(int mealNo) throws SQLException;
	int deleteMealFoodByMealFoodNo(int mealFoodNo) throws SQLException;
}
