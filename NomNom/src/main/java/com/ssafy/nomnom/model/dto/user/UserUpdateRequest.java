package com.ssafy.nomnom.model.dto.user;

import java.time.LocalDate;
import java.util.List;

public class UserUpdateRequest {

    private int userNo;                     // 유저 PK
    private String userPw;                  // 비밀번호
    private LocalDate userBirthday;         // 생년월일
    private String userGender;              // 성별 (예: "MALE", "FEMALE")
    private Integer userHeight;             // 키 (cm)
    private Integer userWeight;             // 몸무게 (kg)

    private List<Integer> allergyIds;       // 알러지 ID 리스트

    // Getter & Setter
    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

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

    public List<Integer> getAllergyIds() {
        return allergyIds;
    }

    public void setAllergyIds(List<Integer> allergyIds) {
        this.allergyIds = allergyIds;
    }
}

