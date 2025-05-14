package com.ssafy.nomnom.model.service;

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

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private AttachmentTargetEnum attachmentTargetEnum;

	@Override
	public List<MealResponse> mealList(Meal meal) throws Exception {
		return mealDao.selectMealByUserAndRegDate(meal);
	}

	@Override
	public List<MealFood> mealFoodList(int mealNo) throws Exception {
		return mealDao.selectFoodByMealFoodNo(mealNo);
	}

	@Transactional
	@Override
	public void writeMeal(Meal meal) throws Exception {
		mealDao.insertMeal(meal);
		
		for (MealFood food : meal.getMealFoodList()) {
		    food.setMealNo(meal.getMealNo());
		    mealDao.insertMealFood(food);
		}
		
		for (MultipartFile mpfile : meal.getFileList()) {
			attachmentService.writeAttachment(mpfile, attachmentTargetEnum.BOARD, meal.getMealNo());
		}
	}

	@Override
	public void removeMeal(int mealNo) throws Exception {
		mealDao.deleteMeal(mealNo);
		mealDao.deleteMealFood(mealNo);
	}

	@Override
	public void removeMealFood(int mealFoodNo) throws Exception {
		mealDao.deleteMealFoodByMealFoodNo(mealFoodNo);
	}
}
