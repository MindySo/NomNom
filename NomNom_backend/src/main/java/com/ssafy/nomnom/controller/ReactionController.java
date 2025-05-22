package com.ssafy.nomnom.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nomnom.model.dto.user.CustomUserDetails;
import com.ssafy.nomnom.model.service.community.ReactionService;

@RestController
@RequestMapping("/api/boards/{boardNo}/reactions")
public class ReactionController {
	
	private final ReactionService reactionService;
	public ReactionController(ReactionService reactionService) {
		this.reactionService = reactionService;
	}
	
    // 좋아요 토글
    @PostMapping
    public ResponseEntity<?> toggleReaction(@PathVariable int boardNo,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        int userNo = userDetails.getUserNo();
        boolean added = reactionService.toggleReaction(boardNo, userNo);
        String message = added ? "좋아요 등록 완료" : "좋아요 취소 완료";
        return ResponseEntity.ok(Map.of("message", message));
    }

    // 좋아요 수 조회
    @GetMapping("/count")
    public ResponseEntity<?> getReactionCount(@PathVariable int boardNo) {
        int count = reactionService.getReactionCount(boardNo);
        return ResponseEntity.ok(Map.of("count", count));
    }

    // 내가 좋아요 눌렀는지 여부
    @GetMapping("/me")
    public ResponseEntity<?> hasUserReacted(@PathVariable int boardNo,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        int userNo = userDetails.getUserNo();
        boolean reacted = reactionService.hasUserReacted(boardNo, userNo);
        return ResponseEntity.ok(Map.of("reacted", reacted));
    }
}
