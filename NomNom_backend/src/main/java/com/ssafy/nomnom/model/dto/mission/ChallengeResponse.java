package com.ssafy.nomnom.model.dto.mission;

import java.time.LocalDate;

public class ChallengeResponse {
	private Integer challengeNo;
    private Integer userNo;
    private Integer missionNo; 
    private String missionName; 
    private LocalDate challengeStartDate;
    private Integer challengeCurrentStreak;
    private ChallengeStatusEnum challengeStatus;
    private String imageUrl;
    private String missionColor;

    
    public ChallengeResponse() {
	}


	public ChallengeResponse(Integer challengeNo, Integer userNo, Integer missionNo, String missionName,
			LocalDate challengeStartDate, Integer challengeCurrentStreak, ChallengeStatusEnum challengeStatus,
			String imageUrl, String missionColor) {
		super();
		this.challengeNo = challengeNo;
		this.userNo = userNo;
		this.missionNo = missionNo;
		this.missionName = missionName;
		this.challengeStartDate = challengeStartDate;
		this.challengeCurrentStreak = challengeCurrentStreak;
		this.challengeStatus = challengeStatus;
		this.imageUrl = imageUrl;
		this.missionColor = missionColor;
	}


	public Integer getChallengeNo() {
		return challengeNo;
	}


	public void setChallengeNo(Integer challengeNo) {
		this.challengeNo = challengeNo;
	}


	public Integer getUserNo() {
		return userNo;
	}


	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}


	public Integer getMissionNo() {
		return missionNo;
	}


	public void setMissionNo(Integer missionNo) {
		this.missionNo = missionNo;
	}


	public String getMissionName() {
		return missionName;
	}


	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}


	public LocalDate getChallengeStartDate() {
		return challengeStartDate;
	}


	public void setChallengeStartDate(LocalDate challengeStartDate) {
		this.challengeStartDate = challengeStartDate;
	}


	public Integer getChallengeCurrentStreak() {
		return challengeCurrentStreak;
	}


	public void setChallengeCurrentStreak(Integer challengeCurrentStreak) {
		this.challengeCurrentStreak = challengeCurrentStreak;
	}


	public ChallengeStatusEnum getChallengeStatus() {
		return challengeStatus;
	}


	public void setChallengeStatus(ChallengeStatusEnum challengeStatus) {
		this.challengeStatus = challengeStatus;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getMissionColor() {
		return missionColor;
	}


	public void setMissionColor(String missionColor) {
		this.missionColor = missionColor;
	}


	@Override
	public String toString() {
		return "ChallengeResponse [challengeNo=" + challengeNo + ", userNo=" + userNo + ", missionNo=" + missionNo
				+ ", missionName=" + missionName + ", challengeStartDate=" + challengeStartDate
				+ ", challengeCurrentStreak=" + challengeCurrentStreak + ", challengeStatus=" + challengeStatus
				+ ", imageUrl=" + imageUrl + ", missionColor=" + missionColor + "]";
	}

}
