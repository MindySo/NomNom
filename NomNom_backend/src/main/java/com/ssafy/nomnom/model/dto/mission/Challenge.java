package com.ssafy.nomnom.model.dto.mission;

import java.time.LocalDate;

public class Challenge {
	private Integer challengeNo;
    private Integer userNo;
    private Integer missionNo;
    private LocalDate challengeStartDate;
    private Integer challengeCurrentStreak;
    private ChallengeStatusEnum challengeStatus;

    
    public Challenge() {
	}


	public Challenge(Integer challengeNo, Integer userNo, Integer missionNo, LocalDate challengeStartDate,
			Integer challengeCurrentStreak, ChallengeStatusEnum challengeStatus) {
		this.challengeNo = challengeNo;
		this.userNo = userNo;
		this.missionNo = missionNo;
		this.challengeStartDate = challengeStartDate;
		this.challengeCurrentStreak = challengeCurrentStreak;
		this.challengeStatus = challengeStatus;
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


	@Override
	public String toString() {
		return "ChallengeResponse [challengeNo=" + challengeNo + ", userNo=" + userNo + ", missionNo=" + missionNo
				+ ", challengeStartDate=" + challengeStartDate + ", challengeCurrentStreak=" + challengeCurrentStreak
				+ ", challengeStatus=" + challengeStatus + "]";
	}
}
