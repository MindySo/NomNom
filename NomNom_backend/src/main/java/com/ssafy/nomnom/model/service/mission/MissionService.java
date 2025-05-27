package com.ssafy.nomnom.model.service.mission;

import java.util.List;

import com.ssafy.nomnom.model.dto.mission.Challenge;
import com.ssafy.nomnom.model.dto.mission.MissionResponse;

public interface MissionService {
	List<MissionResponse> getMissionsByUser(int userNo);
	List<Challenge> getChallengesByUser(int userNo);
	List<Challenge> getChallengesInProgressByUser(int userNo);
	List<Challenge> getChallengesCompletedByUser(int userNo);
	void writeChallenge(Challenge challenge);
	void updateChallengeDropped(int userNo);
	void updateChallengeFailed(int userNo);
	void updateChallengeCompleted(int userNo);
}
