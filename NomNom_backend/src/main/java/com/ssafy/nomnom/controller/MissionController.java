package com.ssafy.nomnom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.nomnom.model.dto.mission.ChallengeResponse;
import com.ssafy.nomnom.model.dto.mission.MissionResponse;
import com.ssafy.nomnom.model.service.mission.MissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api/mission")
@Tag(name = "미션 API", description = "미션 crud")
public class MissionController {
	private MissionService missionService;

	public MissionController(MissionService missionService) {
		this.missionService = missionService;
	}
	
	@Operation(summary = "모든 미션 조회(유저별 미션 달성 횟수 및 진행 상태 포함)")
	@GetMapping("/list")
	public ResponseEntity<List<MissionResponse>> getDailyReport(@RequestParam int userNo){
		List<MissionResponse> missionList = missionService.getMissionsByUser(userNo);
		return ResponseEntity.ok(missionList);
	}
	
	@Operation(summary = "유저의 챌린지 내역 전체 조회")
	@GetMapping("/challenge/all")
	public ResponseEntity<List<ChallengeResponse>> getChallengesByUser(@RequestParam int userNo){
		List<ChallengeResponse> challengeList = missionService.getChallengesByUser(userNo);
		return ResponseEntity.ok(challengeList);
	}
	
	@Operation(summary = "유저의 진행중인 챌린지 조회")
	@GetMapping("/challenge/inprogress")
	public ResponseEntity<List<ChallengeResponse>> getChallengesInProgressByUser(@RequestParam int userNo){
		List<ChallengeResponse> challengeList = missionService.getChallengesInProgressByUser(userNo);
		return ResponseEntity.ok(challengeList);
	}
	
	@Operation(summary = "유저의 달성한 챌린지 조회")
	@GetMapping("/challenge/completed")
	public ResponseEntity<List<ChallengeResponse>> getChallengesCompletedByUser(@RequestParam int userNo){
		List<ChallengeResponse> challengeList = missionService.getChallengesCompletedByUser(userNo);
		return ResponseEntity.ok(challengeList);
	}
	
	@Operation(summary = "챌린지 등록")
	@PostMapping("/challenge")
	public ResponseEntity<Void> writeChallenge(ChallengeResponse challenge) {
		missionService.writeChallenge(challenge);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@Operation(summary = "챌린지 상태 변경 - 중도 포기")
	@PutMapping("/challenge/dropped")
	public ResponseEntity<Void> updateChallengeDropped(int userNo) {
		missionService.updateChallengeDropped(userNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Operation(summary = "챌린지 상태 변경 - 실패")
	@PutMapping("/challenge/failed")
	public ResponseEntity<Void> updateChallengeFailed(int userNo) {
		missionService.updateChallengeFailed(userNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Operation(summary = "챌린지 상태 변경 - 성공")
	@PutMapping("/challenge/completed")
	public ResponseEntity<Void> updateChallengeCompleted(int userNo) {
		missionService.updateChallengeCompleted(userNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	

}
