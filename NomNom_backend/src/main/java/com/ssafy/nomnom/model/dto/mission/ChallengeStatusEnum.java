package com.ssafy.nomnom.model.dto.mission;

public enum ChallengeStatusEnum {
	IN_PROGRESS, COMPLETED, FAILED, DROPPED;
	
	@Override
    public String toString() {
        return this.name();
    }
}
