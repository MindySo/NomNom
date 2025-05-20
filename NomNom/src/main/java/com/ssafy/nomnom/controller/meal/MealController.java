package com.ssafy.nomnom.controller.meal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dto.meal.Meal;
import com.ssafy.nomnom.model.dto.meal.MealFood;
import com.ssafy.nomnom.model.dto.meal.MealResponse;
import com.ssafy.nomnom.model.service.meal.MealService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/meal")
@CrossOrigin("*")
@Tag(name = "식단 API", description = "식단 crud")
public class MealController {
	private final MealService mealservice;

	public MealController(MealService mealservice) {
		this.mealservice = mealservice;
	}

	@Operation(summary = "날짜 별 유저 식단 조회")
	@GetMapping("/meal")
	public ResponseEntity<List<MealResponse>> getMealList(
			@RequestParam int userNo, 
			@Parameter(example = "2025-05-14") @RequestParam String mealRegDate){
		Meal meal = new Meal();
		meal.setUserNo(userNo);
		meal.setMealRegDate(LocalDate.parse(mealRegDate));
		List<MealResponse> list = mealservice.getMealList(meal);
		return ResponseEntity.ok(list);
	}
	
	@Operation(summary = "식단에 포함된 음식 조회")
	@GetMapping("/foods")
	public ResponseEntity<List<MealFood>> getMealFoodList(
			@RequestParam int mealNo){
		Meal meal = new Meal();
		List<MealFood> list = mealservice.getMealFoodList(mealNo);
		return ResponseEntity.ok(list);
	}
	
	@Operation(summary = "식단 등록")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> writeMeal(
			@RequestPart("meal") Meal meal,
			@RequestPart(value = "fileList", required = false) List<MultipartFile> fileList
			) {
		meal.setFileList(fileList);
		mealservice.writeMeal(meal);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Operation(summary = "식단 및 식단에 포함된 모든 음식 삭제")
	@DeleteMapping("meal/{mealNo}")
	public ResponseEntity<Void> deleteMeal(@PathVariable("mealNo") int mealNo){
		mealservice.removeMeal(mealNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Operation(summary = "식단에 포함된 음식 삭제")
	@DeleteMapping("food/{mealNo}")
	public ResponseEntity<Void> deleteMealFood(@PathVariable("mealFoodNo") int mealFoodNo){
		mealservice.removeMeal(mealFoodNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
