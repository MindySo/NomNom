package com.ssafy.nomnom.model.dto.meal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MealRequest {
	private int mealNo;
	private int userNo;
	private LocalDate mealRegDate;
	private MealTimeEnum mealTime;
	private String mealTitle;
	private String mealContent;
	private List<MealFood> mealFoodList; 
	private List<MultipartFile> fileList; 

	
	public MealRequest() {
	}


	public MealRequest(int mealNo, int userNo, LocalDate mealRegDate, MealTimeEnum mealTime, String mealTitle,
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


	public int getMealNo() {
		return mealNo;
	}


	public void setMealNo(int mealNo) {
		this.mealNo = mealNo;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public LocalDate getMealRegDate() {
		return mealRegDate;
	}


	public void setMealRegDate(LocalDate mealRegDate) {
		this.mealRegDate = mealRegDate;
	}


	public MealTimeEnum getMealTime() {
		return mealTime;
	}


	public void setMealTime(MealTimeEnum mealTime) {
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
		return "MealRequest [mealNo=" + mealNo + ", userNo=" + userNo + ", mealRegDate=" + mealRegDate + ", mealTime="
				+ mealTime + ", mealTitle=" + mealTitle + ", mealContent=" + mealContent + ", mealFoodList=" + mealFoodList
				+ ", fileList=" + fileList + "]";
	}
	
	
}
