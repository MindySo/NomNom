package com.ssafy.nomnom.model.dto.mission;

import java.time.LocalDate;

public class Badge {
	private Integer badgeNo;
    private Integer userNo;
    private Integer missionNo;
    private LocalDate badgeRegDate;
    
	public Badge() {
	}	
	
	public Badge(Integer badgeNo, Integer userNo, Integer missionNo, LocalDate badgeRegDate) {
		this.badgeNo = badgeNo;
		this.userNo = userNo;
		this.missionNo = missionNo;
		this.badgeRegDate = badgeRegDate;
	}
	
	
	public Integer getBadgeNo() {
		return badgeNo;
	}
	public void setBadgeNo(Integer badgeNo) {
		this.badgeNo = badgeNo;
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
	public LocalDate getBadgeRegDate() {
		return badgeRegDate;
	}
	public void setBadgeRegDate(LocalDate badgeRegDate) {
		this.badgeRegDate = badgeRegDate;
	}
	@Override
	public String toString() {
		return "Badge [badgeNo=" + badgeNo + ", userNo=" + userNo + ", missionNo=" + missionNo + ", badgeRegDate="
				+ badgeRegDate + "]";
	}
}
