package com.ssafy.mvc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.model.service.MealService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/meal")
@CrossOrigin("*")
@Tag(name="식단 API", description="식단 crud")
public class MealController {
	private final MealService mealservice;

	public MealController(MealService mealservice) {
		this.mealservice = mealservice;
	}
	
	@GetMapping
	public String getMealList(String param) {
		return new String();
	}
	
	
	
}
