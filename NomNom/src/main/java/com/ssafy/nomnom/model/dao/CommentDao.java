package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.community.Comment;

@Repository
public interface CommentDao {
	//댓글 등록  
	int insertComment(Comment comment);
	
	//댓글 수정 
	int updateComment(Comment comment);
	
	//댓글 삭제 
	int deleteComment(@Param("commentNo") int commentNo, @Param("userNo") int userNo);
	
	//댓글 조회(마이페이지에서) 
	List<Comment> selectCommentsByUser(@Param("userNo") int userNo);
	
	// 게시글 상세 조회 시 댓글 전체 조회 (대댓글 포함 계층 구조)
	List<Comment> selectCommentsByBoard(@Param("boardNo") int boardNo);
}