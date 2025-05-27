package com.ssafy.nomnom.model.dto.mission;

public class MissionResponse {

    private int missionNo;
    private String missionName;
    private String missionDescription;
    private int challengeDuration;
    private boolean isInProgress;
    private int completedCount;
    
	public MissionResponse() {
	}
	public MissionResponse(int missionNo, String missionName, String missionDescription, int challengeDuration,
			boolean isInProgress, int completedCount) {
		this.missionNo = missionNo;
		this.missionName = missionName;
		this.missionDescription = missionDescription;
		this.challengeDuration = challengeDuration;
		this.isInProgress = isInProgress;
		this.completedCount = completedCount;
	}
	public int getMissionNo() {
		return missionNo;
	}
	public void setMissionNo(int missionNo) {
		this.missionNo = missionNo;
	}
	public String getMissionName() {
		return missionName;
	}
	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}
	public String getMissionDescription() {
		return missionDescription;
	}
	public void setMissionDescription(String missionDescription) {
		this.missionDescription = missionDescription;
	}
	public int getChallengeDuration() {
		return challengeDuration;
	}
	public void setChallengeDuration(int challengeDuration) {
		this.challengeDuration = challengeDuration;
	}
	public boolean isInProgress() {
		return isInProgress;
	}
	public void setInProgress(boolean isInProgress) {
		this.isInProgress = isInProgress;
	}
	public int getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}
	@Override
	public String toString() {
		return "MissionResponse [missionNo=" + missionNo + ", missionName=" + missionName + ", missionDescription="
				+ missionDescription + ", challengeDuration=" + challengeDuration + ", isInProgress=" + isInProgress
				+ ", completedCount=" + completedCount + "]";
	}
    
    
    
}
