package com.ssafy.nomnom.model.dto.mission;

public class Mission {
	private Integer missionId;
    private String missionName;
    private String missionDescription;
    private Integer challengeDuration;

    
    public Mission() {
	}
	
    
    public Mission(Integer missionId, String missionName, String missionDescription, Integer challengeDuration) {
		this.missionId = missionId;
		this.missionName = missionName;
		this.missionDescription = missionDescription;
		this.challengeDuration = challengeDuration;
	}
	
    
    public Integer getMissionId() {
		return missionId;
	}
	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
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
	public Integer getChallengeDuration() {
		return challengeDuration;
	}
	public void setChallengeDuration(Integer challengeDuration) {
		this.challengeDuration = challengeDuration;
	}
	@Override
	public String toString() {
		return "Mission [missionId=" + missionId + ", missionName=" + missionName + ", missionDescription="
				+ missionDescription + ", challengeDuration=" + challengeDuration + "]";
	}
}
