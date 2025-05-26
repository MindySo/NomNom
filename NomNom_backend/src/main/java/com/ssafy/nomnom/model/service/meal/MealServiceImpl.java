package com.ssafy.nomnom.model.service.meal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dao.MealDao;
import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;
import com.ssafy.nomnom.model.dto.meal.Food;
import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.dto.meal.SimpleFoodResponse;
import com.ssafy.nomnom.model.dto.meal.Water;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealRequest;
import com.ssafy.nomnom.model.service.attachment.AttachmentService;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;

	@Autowired
	private AttachmentService attachmentService;

	@Override
	public List<MealResponse> getMealList(MealRequest meal) {
		List<MealResponse> list = mealDao.selectMealByUserAndRegDate(meal);
		
		for(MealResponse mealResponse : list) {
			mealResponse.setFileList(attachmentService.getAttachment(AttachmentTargetEnum.MEAL, mealResponse.getMealNo()));
		}
		
		return list;
	}

	@Override
	public MealResponse getMeal(int mealNo) {
		MealResponse MealResponse = mealDao.selectMealAndNutriSumByMealNo(mealNo);
		MealResponse.setFoodList(mealDao.selectFoodsByMealNo(mealNo));

		return MealResponse;
	}

	@Transactional
	@Override
	public void writeMeal(MealRequest meal) {
		mealDao.insertMeal(meal);

		for (MealFood mealFood : meal.getMealFoodList()) {
			mealFood.setMealNo(meal.getMealNo());
			mealDao.insertMealFood(mealFood);
		}

		for (MultipartFile mpfile : meal.getFileList()) {
			attachmentService.writeAttachment(mpfile, AttachmentTargetEnum.MEAL, meal.getMealNo());
		}
	}


	@Override
	public void writeMealFood(MealFood mealFood) {
		mealDao.insertMealFood(mealFood);
	};
	
	@Override
	public void updateMeal(Meal meal) {
		mealDao.updateMeal(meal);
	}

	@Override
	public void removeMeal(int mealNo) {
		mealDao.deleteMeal(mealNo);
		mealDao.deleteMealFoodByMealNo(mealNo);
		attachmentService.deleteAllAttachment(AttachmentTargetEnum.BOARD, mealNo);
	}

	@Override
	public void removeMealFood(int mealFoodNo) {
		mealDao.deleteMealFoodByMealFoodNo(mealFoodNo);
	}

	@Override
	public List<SimpleFoodResponse> searchFoodList(String foodName) {
		return mealDao.selectFoodByFoodName(foodName);
	}

	@Override
	public Food getFood(String foodCode) {
		return mealDao.selectFoodByFoodCode(foodCode);
	}

	@Override
	public List<Water> getWaterListByUserAndRegDate(MealRequest meal){
		return mealDao.selectWaterListByUserAndRegDate(meal);
	}

}
