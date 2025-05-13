package com.ssafy.mvc.model.dto;

import java.time.LocalDateTime;

public class PointLog {
	private Integer pointLogNo;
    private Integer userNo;
    private Integer pointAmount;
    private String pointType;
    private String pointTarget;
    private LocalDateTime pointRegDate;
    
    
	public PointLog() {
	}
	
	
	public PointLog(Integer pointLogNo, Integer userNo, Integer pointAmount, String pointType, String pointTarget,
			LocalDateTime pointRegDate) {
		this.pointLogNo = pointLogNo;
		this.userNo = userNo;
		this.pointAmount = pointAmount;
		this.pointType = pointType;
		this.pointTarget = pointTarget;
		this.pointRegDate = pointRegDate;
	}
	
	
	public Integer getPointLogNo() {
		return pointLogNo;
	}
	public void setPointLogNo(Integer pointLogNo) {
		this.pointLogNo = pointLogNo;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public Integer getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(Integer pointAmount) {
		this.pointAmount = pointAmount;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public String getPointTarget() {
		return pointTarget;
	}
	public void setPointTarget(String pointTarget) {
		this.pointTarget = pointTarget;
	}
	public LocalDateTime getPointRegDate() {
		return pointRegDate;
	}
	public void setPointRegDate(LocalDateTime pointRegDate) {
		this.pointRegDate = pointRegDate;
	}
	@Override
	public String toString() {
		return "PointLog [pointLogNo=" + pointLogNo + ", userNo=" + userNo + ", pointAmount=" + pointAmount
				+ ", pointType=" + pointType + ", pointTarget=" + pointTarget + ", pointRegDate=" + pointRegDate + "]";
	}
}
