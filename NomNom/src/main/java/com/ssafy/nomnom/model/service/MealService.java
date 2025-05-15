package com.ssafy.nomnom.model.service;

import java.util.List;

import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;

public interface MealService {
	List<MealResponse> mealList(Meal meal) throws Exception;
	List<MealFood> mealFoodList(int mealNo) throws Exception;
	void writeMeal(Meal meal) throws Exception;
	void removeMeal(int no) throws Exception;
	void removeMealFood(int no) throws Exception;
}
