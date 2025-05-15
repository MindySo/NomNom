package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;

@Repository
public interface MealDao {
	List<MealResponse> selectMealByUserAndRegDate(Meal meal);
	List<MealFood> selectFoodByMealFoodNo(int mealNo);
	int insertMeal(Meal meal);
	int insertMealFood(MealFood mealFood);
	int deleteMeal(int mealNo);
	int deleteMealFood(int mealNo);
	int deleteMealFoodByMealFoodNo(int mealFoodNo);
}
