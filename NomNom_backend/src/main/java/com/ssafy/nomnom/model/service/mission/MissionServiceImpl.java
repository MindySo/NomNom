package com.ssafy.nomnom.model.service.mission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.nomnom.model.dao.MissionDao;
import com.ssafy.nomnom.model.dto.mission.ChallengeResponse;
import com.ssafy.nomnom.model.dto.mission.MissionResponse;

@Service
public class MissionServiceImpl implements MissionService{
	@Autowired
	private MissionDao missionDao;

	@Override
	public List<MissionResponse> getMissionsByUser(int userNo) {
		return missionDao.selectAllMissionByUser(userNo);
	}
	
	@Override
	public List<ChallengeResponse> getChallengesByUser(int userNo) {
		return missionDao.selectAllChallengeByUser(userNo);
	}
	
	@Override
	public List<ChallengeResponse> getChallengesInProgressByUser(int userNo) {
		return missionDao.selectAllChallengeInProgressByUser(userNo);
	}
	
	@Override
	public List<ChallengeResponse> getChallengesCompletedByUser(int userNo) {
		return missionDao.selectAllChallengeCompletedByUser(userNo);
	}

	@Override
	public void writeChallenge(ChallengeResponse challenge) {
		missionDao.insertChallenge(challenge);
	}

	@Override
	public void updateChallengeDropped(int userNo) {
		missionDao.updateChallengeDropped(userNo);		
	}

	@Override
	public void updateChallengeFailed(int userNo) {
		missionDao.updateChallengeFailed(userNo);
	}

	@Override
	public void updateChallengeCompleted(int userNo) {
		missionDao.updateChallengeCompleted(userNo);
	}

}
