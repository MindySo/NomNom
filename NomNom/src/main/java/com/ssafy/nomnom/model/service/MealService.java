package com.ssafy.nomnom.model.service;

import java.util.List;

import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;

public interface MealService {
	List<MealResponse> getMealList(Meal meal);
	List<MealFood> getMealFoodList(int mealNo);
	void writeMeal(Meal meal);
	void removeMeal(int no);
	void removeMealFood(int no);
}
