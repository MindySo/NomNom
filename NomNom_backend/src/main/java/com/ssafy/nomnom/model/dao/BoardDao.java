package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.community.Board;
import com.ssafy.nomnom.model.dto.community.BoardSearchCondition;

@Repository
public interface BoardDao {

	// 전체 게시글 조회
	List<Board> selectAllBoards();

	// 단일 게시글 조회
	Board selectBoardById(@Param("boardNo") int boardNo);
	
	//유저별 조회(마이페이지) 
    List<Board> selectBoardsByUser(@Param("userNo") int userNo);
    
    //카테고리별 조회 
    List<Board> selectBoardsByType(@Param("boardType") String boardType);

	// 게시글 등록
	int insertBoard(Board board);

	// 게시글 수정
	int updateBoard(Board board);

	// 게시글 삭제
	int deleteBoard(@Param("boardNo") int boardNo, @Param("userNo") int userNo);

	//검색 
	List<Board> searchBoards(@Param("cond") BoardSearchCondition cond);
}
