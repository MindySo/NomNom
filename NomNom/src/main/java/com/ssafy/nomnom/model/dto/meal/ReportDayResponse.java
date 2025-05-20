package com.ssafy.nomnom.model.dto.meal;

import java.time.LocalDate;

public class ReportDayResponse {
	private LocalDate reportDate;
	private double energy;              // 칼로리 (Energy, kcal)
	private double carbohydrate;        // 탄수화물
	private double sugar;               // 당류
	private double dietaryFiber;        // 식이섬유
	private double protein;             // 단백질
	private double ash;                 // 회분
	private double totalFattyAcids;     // 총 지방산
	private double saturatedFats;       // 포화지방산
	private double unsaturatedFats;     // 단일불포화지방산 + 다중불포화지방산
	private double water;               // 수분
	private double vitaminACarotene;    // 비타민 A (총 카로틴계)
	private double vitaminB1;           // 비타민 B1 (Thiamine)
	private double vitaminB2;           // 비타민 B2 (Riboflavin)
	private double niacin;              // 비타민 B3 나이아신
	private double folate;              // 엽산 (Folate)
	private double vitaminC;            // 비타민 C
	private double vitaminD;            // 비타민 D
	private double cholesterol;         // 콜레스테롤
	private double sodium;              // 나트륨
	private double calcium;             // 칼슘
	private double iron;                // 철
	private double phosphorus;          // 인
	private double potassium;           // 칼륨

}
