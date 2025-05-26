package com.ssafy.nomnom.model.service.mission;

import java.util.List;

import com.ssafy.nomnom.model.dto.mission.ChallengeResponse;
import com.ssafy.nomnom.model.dto.mission.MissionResponse;

public interface MissionService {
	List<MissionResponse> getMissionsByUser(int userNo);
	List<ChallengeResponse> getChallengesByUser(int userNo);
	List<ChallengeResponse> getChallengesInProgressByUser(int userNo);
	List<ChallengeResponse> getChallengesCompletedByUser(int userNo);
	void writeChallenge(ChallengeResponse challenge);
	void updateChallengeDropped(int userNo);
	void updateChallengeFailed(int userNo);
	void updateChallengeCompleted(int userNo);
}
