package com.ssafy.nomnom.model.dto.mission;

public class Mission {
	private Integer missionNo;
	private String missionName;
	private String missionDescription;
	private Integer challengeDuration;
	private String imageUrl;
	private String missionColor;

	public Mission() {
	}

	public Mission(Integer missionNo, String missionName, String missionDescription, Integer challengeDuration,
			String imageUrl, String missionColor) {
		this.missionNo = missionNo;
		this.missionName = missionName;
		this.missionDescription = missionDescription;
		this.challengeDuration = challengeDuration;
		this.imageUrl = imageUrl;
		this.missionColor = missionColor;
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
		return "Mission [missionNo=" + missionNo + ", missionName=" + missionName + ", missionDescription="
				+ missionDescription + ", challengeDuration=" + challengeDuration + ", imageUrl=" + imageUrl
				+ ", missionColor=" + missionColor + "]";
	}
}
