package com.ssafy.nomnom.model.dto.mission;

import java.time.LocalDate;

public class Badge {
	private Integer badgeId;
    private Integer userId;
    private Integer missionId;
    private LocalDate badgeRegDate;
    
	public Badge() {
	}	
	
	public Badge(Integer badgeId, Integer userId, Integer missionId, LocalDate badgeRegDate) {
		this.badgeId = badgeId;
		this.userId = userId;
		this.missionId = missionId;
		this.badgeRegDate = badgeRegDate;
	}
	
	
	public Integer getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(Integer badgeId) {
		this.badgeId = badgeId;
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
	public LocalDate getBadgeRegDate() {
		return badgeRegDate;
	}
	public void setBadgeRegDate(LocalDate badgeRegDate) {
		this.badgeRegDate = badgeRegDate;
	}
	@Override
	public String toString() {
		return "Badge [badgeId=" + badgeId + ", userId=" + userId + ", missionId=" + missionId + ", badgeRegDate="
				+ badgeRegDate + "]";
	}
}
