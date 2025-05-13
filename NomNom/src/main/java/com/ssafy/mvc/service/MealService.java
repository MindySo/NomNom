package com.ssafy.mvc.service;

import java.util.List;

import com.ssafy.mvc.model.dto.Meal;
import com.ssafy.mvc.model.dto.MealFood;
import com.ssafy.mvc.model.dto.MealResponse;

public interface MealService {
	List<MealResponse> mealList(Meal meal) throws Exception;
	List<MealFood> mealFoodList(int mealNo) throws Exception;
	void writeMeal(Meal meal) throws Exception;
	void removeMeal(int no) throws Exception;
	void removeMealFood(int no) throws Exception;
}
