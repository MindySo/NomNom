package com.ssafy.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.dao.MealDao;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;
}
