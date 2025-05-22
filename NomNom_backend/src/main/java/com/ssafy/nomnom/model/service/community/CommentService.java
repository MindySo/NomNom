package com.ssafy.nomnom.model.service.community;

import java.util.List;

import com.ssafy.nomnom.model.dto.community.Comment;

public interface CommentService {
	
	//댓글 등록 
    boolean insertComment(Comment comment);
    
    //댓글 수정 
    boolean updateComment(Comment comment);
    
    //댓글삭제 
    boolean deleteComment(int commentNo, int userNo);
    
    //마이페이지에서 댓글 조회
    List<Comment> getCommentsByUser(int userNo);
    
    //게시글에서 댓글 조회 
    List<Comment> getCommentsByBoard(int boardNo);
}