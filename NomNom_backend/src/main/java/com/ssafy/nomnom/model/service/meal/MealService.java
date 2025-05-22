package com.ssafy.nomnom.model.service.meal;

import java.util.List;

import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.SimpleFoodResponse;
import com.ssafy.nomnom.model.dto.meal.Food;
import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.dto.meal.MealResponse;

public interface MealService {
	List<MealResponse> getMealList(MealRequest meal);

	MealResponse getMeal(int mealNo);

	void writeMeal(MealRequest meal);

	void updateMeal(Meal meal);

	void removeMeal(int no);

	void removeMealFood(int no);

	List<SimpleFoodResponse> searchFoodList(String foodName);

	Food getFood(String foodCode);

	List<MealResponse> getWaterListByUserAndRegDate(MealRequest meal);
}
