package com.ssafy.nomnom.model.service.meal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dao.MealDao;
import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;
import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.service.attachment.AttachmentService;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;

	@Autowired
	private AttachmentService attachmentService;

	@Override
	public List<MealResponse> getMealList(Meal meal) {
		return mealDao.selectMealByUserAndRegDate(meal);
	}

	@Override
	public List<MealFood> getMealFoodList(int mealNo) {
		return mealDao.selectFoodByMealFoodNo(mealNo);
	}

	@Transactional
	@Override
	public void writeMeal(Meal meal) {
		mealDao.insertMeal(meal);
		
		for (MealFood food : meal.getMealFoodList()) {
		    food.setMealNo(meal.getMealNo());
		    mealDao.insertMealFood(food);
		}
		
		for (MultipartFile mpfile : meal.getFileList()) {
			attachmentService.writeAttachment(mpfile, AttachmentTargetEnum.BOARD, meal.getMealNo());
		}
	}

	@Override
	public void removeMeal(int mealNo) {
		mealDao.deleteMeal(mealNo);
		mealDao.deleteMealFoodByMealNo(mealNo);
	}

	@Override
	public void removeMealFood(int mealFoodNo) {
		mealDao.deleteMealFoodByMealFoodNo(mealFoodNo);
	}
}
