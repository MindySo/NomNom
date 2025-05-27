package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.mission.Challenge;
import com.ssafy.nomnom.model.dto.mission.MissionResponse;

@Repository
public interface MissionDao {
	List<MissionResponse> selectAllMissionByUser(int userNo);
	List<Challenge> selectAllChallengeByUser(int userNo);
	List<Challenge> selectAllChallengeInProgressByUser(int userNo);
	List<Challenge> selectAllChallengeCompletedByUser(int userNo);
	void insertChallenge(Challenge challenge);
	void updateChallengeDropped(int userNo);
	void updateChallengeFailed(int userNo);
	void updateChallengeCompleted(int userNo);
}
