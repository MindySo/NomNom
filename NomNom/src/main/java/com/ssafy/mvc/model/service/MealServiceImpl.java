package com.ssafy.mvc.model.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.mvc.model.dao.MealDao;
import com.ssafy.mvc.model.dto.Attachment;
import com.ssafy.mvc.model.dto.Meal;
import com.ssafy.mvc.model.dto.MealFood;
import com.ssafy.mvc.model.dto.MealResponse;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;

	@Override
	public List<MealResponse> mealList(Meal meal) throws Exception {
		return mealDao.selectMealByUserAndRegDate(meal);
	}

	@Override
	public List<MealFood> mealFoodList(int mealNo) throws Exception {
		return mealDao.selectFoodByMealFoodNo(mealNo);
	}

	@Override
	public void writeMeal(Meal meal) throws Exception {
		mealDao.insertMeal(meal);
		
		for (MealFood food : meal.getMealFoodList()) {
		    food.setMealNo(meal.getMealNo());
		    mealDao.insertMealFood(food);
		}

		for (MultipartFile attach : meal.getFileList()) {
			// AttachService에서 작성할 것
//			if (!attach.isEmpty()) {
//				String originalName = attach.getOriginalFilename();
//				long fileSize = attach.getSize();	// byte 크기
//				String uploadName = generateUniqueName(originalName);
//				File dirFile = new File(uploadDir);
//				if (!dirFile.exists()) {
//					dirFile.mkdirs();
//				}
//				File file = new File(dirFile, uploadName);
//				// 1. 서버에 저장
//				attach.transferTo(file);
//				
//				// 2. 디비에 저장
//				Attachment boardFile = new BoardFile();
//				// no, original_name, upload_name, file_size
//				boardFile.setNo(board.getNo());
//				boardFile.setOriginalName(originalName);
//				boardFile.setUploadName(uploadName);
//				boardFile.setFileSize(fileSize);
//				boardDao.insertBoardFile(boardFile);
//			}
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
