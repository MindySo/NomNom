package com.ssafy.nomnom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dto.community.Board;
import com.ssafy.nomnom.model.dto.community.BoardSearchCondition;
import com.ssafy.nomnom.model.dto.user.CustomUserDetails;
import com.ssafy.nomnom.model.service.community.BoardService;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 전체 게시글 조회
	@GetMapping
	public ResponseEntity<List<Board>> getAllBoards() {
		List<Board> boards = boardService.getAllBoards();
		return ResponseEntity.ok(boards);
	}

	// 단일 게시글 조회
	@GetMapping("/{boardNo}")
	public ResponseEntity<Board> getBoardById(@PathVariable int boardNo) {
		Board board = boardService.getBoardById(boardNo);
		if (board == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(board);
	}

	// 유저별 게시글 조회 (인증 사용자 기준)
	@GetMapping("/my-posts")
	public ResponseEntity<List<Board>> getMyBoards(@AuthenticationPrincipal CustomUserDetails userDetails) {
		int userNo = userDetails.getUserNo();
		List<Board> boards = boardService.getBoardsByUser(userNo);
		return ResponseEntity.ok(boards);
	}

	// 카테고리별 조회
	@GetMapping("/category")
	public ResponseEntity<List<Board>> getBoardsByCategory(@RequestParam String boardType) {
		List<Board> boards = boardService.getBoardsByType(boardType);
		return ResponseEntity.ok(boards);
	}

	// 게시글 등록
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<?> createBoard(@RequestPart("board") Board board,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@AuthenticationPrincipal CustomUserDetails userDetails) {

		board.setUserNo(userDetails.getUserNo()); // 로그인한 사용자 정보 주입
		boolean result = boardService.createBoard(board, file);
		return result ? ResponseEntity.ok().body(Map.of("message", "등록이 완료되었습니다."))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	// 게시글 수정
	@PutMapping(value = "/{boardNo}", consumes = "multipart/form-data")
	public ResponseEntity<?> updateBoard(@PathVariable int boardNo, @RequestPart("board") Board board,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		board.setBoardNo(boardNo);
		board.setUserNo(userDetails.getUserNo()); // 사용자 본인 여부 확인용
		boolean result = boardService.updateBoard(board, file);
		return result ? ResponseEntity.ok().body(Map.of("message", "수정이 완료되었습니다."))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	// 게시글 삭제
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<?> deleteBoard(@PathVariable int boardNo,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		int userNo = userDetails.getUserNo();
		boolean result = boardService.deleteBoard(boardNo, userNo);
		return result ? ResponseEntity.ok().body(Map.of("message", "삭제가 완료되었습니다."))
				: ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
	}

	// 검색
	@GetMapping("/search")
	public ResponseEntity<List<Board>> searchBoards(
			@RequestParam(required = false) String boardType,
			@RequestParam(required = false) String keyword) {
		BoardSearchCondition cond = new BoardSearchCondition();
		cond.setBoardType(boardType);
		cond.setKeyword(keyword);

		List<Board> result = boardService.searchBoards(cond);
		return ResponseEntity.ok(result);
	}

} 
