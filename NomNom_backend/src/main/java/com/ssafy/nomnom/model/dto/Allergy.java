package com.ssafy.nomnom.model.dto;

public class Allergy {
    private int allergyNo;
    private String name;

    public int getAllergyNo() {
        return allergyNo;
    }

    public void setAllergyNo(int allergyNo) {
        this.allergyNo = allergyNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
