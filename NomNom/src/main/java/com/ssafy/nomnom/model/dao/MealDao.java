package com.ssafy.nomnom.model.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;

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
