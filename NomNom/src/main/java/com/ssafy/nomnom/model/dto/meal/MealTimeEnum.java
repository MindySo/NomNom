package com.ssafy.nomnom.model.dto.meal;

public enum MealTimeEnum {
	BREAKFAST, LUNCH, DINNER, SNACK, WATER;
	
	@Override
    public String toString() {
        return this.name();
    }
}
