package com.ssafy.nomnom.model.dto.mission;

import java.time.LocalDate;

public class Challenge {
	private Integer challengeNo;
    private Integer userId;
    private Integer missionId;
    private LocalDate challengeStartDate;
    private Integer challengeCurrentStreak;
    private ChallengeStatusEnum challengeStatus;

    
    public Challenge() {
	}
	
    
    public Challenge(Integer challengeNo, Integer userId, Integer missionId, LocalDate challengeStartDate,
			Integer challengeCurrentStreak, ChallengeStatusEnum challengeStatus) {
		this.challengeNo = challengeNo;
		this.userId = userId;
		this.missionId = missionId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMissionId() {
		return missionId;
	}
	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
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
		return "Challenge [challengeNo=" + challengeNo + ", userId=" + userId + ", missionId=" + missionId
				+ ", challengeStartDate=" + challengeStartDate + ", challengeCurrentStreak=" + challengeCurrentStreak
				+ ", challengeStatus=" + challengeStatus + "]";
	}
}
