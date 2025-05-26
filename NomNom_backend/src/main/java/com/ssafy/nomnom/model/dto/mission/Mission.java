package com.ssafy.nomnom.model.dto.mission;

public class Mission {
	private Integer missionId;
    private String missionName;
    private String missionDescription;
    private Integer challengeDuration;
    private String imageUrl;
    private String missionColor;

    
    public Mission() {
	}


	public Mission(Integer missionId, String missionName, String missionDescription, Integer challengeDuration,
			String imageUrl, String missionColor) {
		this.missionId = missionId;
		this.missionName = missionName;
		this.missionDescription = missionDescription;
		this.challengeDuration = challengeDuration;
		this.imageUrl = imageUrl;
		this.missionColor = missionColor;
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
		return "Mission [missionId=" + missionId + ", missionName=" + missionName + ", missionDescription="
				+ missionDescription + ", challengeDuration=" + challengeDuration + ", imageUrl=" + imageUrl
				+ ", missionColor=" + missionColor + "]";
	}
}
