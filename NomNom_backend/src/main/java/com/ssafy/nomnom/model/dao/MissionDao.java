package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.mission.ChallengeResponse;
import com.ssafy.nomnom.model.dto.mission.MissionResponse;

@Repository
public interface MissionDao {
	List<MissionResponse> selectAllMissionByUser(int userNo);
	List<ChallengeResponse> selectAllChallengeByUser(int userNo);
	List<ChallengeResponse> selectAllChallengeInProgressByUser(int userNo);
	List<ChallengeResponse> selectAllChallengeCompletedByUser(int userNo);
	void insertChallenge(ChallengeResponse challenge);
	void updateChallengeDropped(int userNo);
	void updateChallengeFailed(int userNo);
	void updateChallengeCompleted(int userNo);
}
