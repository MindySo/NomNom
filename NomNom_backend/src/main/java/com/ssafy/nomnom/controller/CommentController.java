package com.ssafy.nomnom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nomnom.model.dto.community.Comment;
import com.ssafy.nomnom.model.dto.community.CommentResponse;
import com.ssafy.nomnom.model.dto.user.CustomUserDetails;
import com.ssafy.nomnom.model.service.community.CommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// 댓글 등록
		@PostMapping
		public ResponseEntity<?> createComment(@RequestBody Comment comment,
											   @AuthenticationPrincipal CustomUserDetails userDetails) {
			comment.setUserNo(userDetails.getUserNo());
			boolean result = commentService.insertComment(comment);
			return result ? ResponseEntity.ok(Map.of("message", "댓글 등록 완료"))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		

		// 댓글 수정
		@PutMapping("/{commentNo}")
		public ResponseEntity<?> updateComment(@PathVariable int commentNo,
											   @RequestBody Comment comment,
											   @AuthenticationPrincipal CustomUserDetails userDetails) {
			comment.setCommentNo(commentNo);
			comment.setUserNo(userDetails.getUserNo());
			boolean result = commentService.updateComment(comment);
			return result ? ResponseEntity.ok(Map.of("message", "댓글 수정 완료"))
					: ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "권한 없음"));
		}

		// 댓글 삭제
		@DeleteMapping("/{commentNo}")
		public ResponseEntity<?> deleteComment(@PathVariable int commentNo,
											   @AuthenticationPrincipal CustomUserDetails userDetails) {
			boolean result = commentService.deleteComment(commentNo, userDetails.getUserNo());
			return result ? ResponseEntity.ok(Map.of("message", "댓글 삭제 완료"))
					: ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "권한 없음"));
		}

		// 내가 쓴 댓글 목록 (마이페이지)
		@GetMapping("/my")
		public ResponseEntity<List<Comment>> getMyComments(@AuthenticationPrincipal CustomUserDetails userDetails) {
			List<Comment> comments = commentService.getCommentsByUser(userDetails.getUserNo());
			return ResponseEntity.ok(comments);
		}

		// 게시글별 전체 댓글 목록
		@GetMapping("/board/{boardNo}")
		public ResponseEntity<List<CommentResponse>> getCommentsByBoard(@PathVariable int boardNo) {
			List<CommentResponse> comments = commentService.getCommentsByBoard(boardNo);
			System.out.println(comments);
			return ResponseEntity.ok(comments);
		}
	}