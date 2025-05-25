package com.ssafy.nomnom.model.dto.user;

import java.time.LocalDate;

public class UserProfileSetupRequest {
    private LocalDate userBirthday;
    private String userGender;
    private Integer userHeight;
    private Integer userWeight;

    public LocalDate getUserBirthday() {
        return userBirthday;
    }
    public void setUserBirthday(LocalDate userBirthday) {
        this.userBirthday = userBirthday;
    }
    public String getUserGender() {
        return userGender;
    }
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    public Integer getUserHeight() {
        return userHeight;
    }
    public void setUserHeight(Integer userHeight) {
        this.userHeight = userHeight;
    }
    public Integer getUserWeight() {
        return userWeight;
    }
    public void setUserWeight(Integer userWeight) {
        this.userWeight = userWeight;
    }
}
