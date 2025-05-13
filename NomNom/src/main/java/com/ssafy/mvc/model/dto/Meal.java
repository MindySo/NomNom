package com.ssafy.mvc.model.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Meal {
	private Integer mealNo;
	private Integer userNo;
	private LocalDate mealRegDate;
	private String mealTime;
	private String mealTitle;
	private String mealContent;
	private List<MealFood> mealFoodList; 
	private List<MultipartFile> fileList; 
	
	public Meal() {
	}
	
	
	public Meal(Integer mealNo, Integer userNo, LocalDate mealRegDate, String mealTime, String mealTitle,
			String mealContent, List<MealFood> mealFoodList, List<MultipartFile> fileList) {
		super();
		this.mealNo = mealNo;
		this.userNo = userNo;
		this.mealRegDate = mealRegDate;
		this.mealTime = mealTime;
		this.mealTitle = mealTitle;
		this.mealContent = mealContent;
		this.mealFoodList = mealFoodList;
		this.fileList = fileList;
	}

	
	public Meal(Integer mealNo, Integer userNo, LocalDate mealRegDate, String mealTime, String mealTitle,
			String mealContent) {
		super();
		this.mealNo = mealNo;
		this.userNo = userNo;
		this.mealRegDate = mealRegDate;
		this.mealTime = mealTime;
		this.mealTitle = mealTitle;
		this.mealContent = mealContent;
	}


	public Meal(Integer mealNo, Integer userNo) {
		super();
		this.mealNo = mealNo;
		this.userNo = userNo;
	}


	public Integer getMealNo() {
		return mealNo;
	}
	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public LocalDate getMealRegDate() {
		return mealRegDate;
	}
	public void setMealRegDate(LocalDate mealRegDate) {
		this.mealRegDate = mealRegDate;
	}
	public String getMealTime() {
		return mealTime;
	}
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	public String getMealTitle() {
		return mealTitle;
	}
	public void setMealTitle(String mealTitle) {
		this.mealTitle = mealTitle;
	}
	public String getMealContent() {
		return mealContent;
	}
	public void setMealContent(String mealContent) {
		this.mealContent = mealContent;
	}
	public List<MealFood> getMealFoodList() {
		return mealFoodList;
	}

	public void setMealFoodList(List<MealFood> mealFoodList) {
		this.mealFoodList = mealFoodList;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	@Override
	public String toString() {
		return "Meal [mealNo=" + mealNo + ", userNo=" + userNo + ", mealRegDate=" + mealRegDate + ", mealTime="
				+ mealTime + ", mealTitle=" + mealTitle + ", mealContent=" + mealContent + ", mealFoodList="
				+ mealFoodList + ", fileList=" + fileList + "]";
	}
}
