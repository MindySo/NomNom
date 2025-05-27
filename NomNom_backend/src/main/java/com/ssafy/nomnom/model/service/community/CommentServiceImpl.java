package com.ssafy.nomnom.model.service.community;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.nomnom.model.dao.CommentDao;
import com.ssafy.nomnom.model.dto.community.Comment;
import com.ssafy.nomnom.model.dto.community.CommentResponse;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentDao commentDao;
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	//댓글 등록 
	@Override
	public boolean insertComment(Comment comment) {
		return commentDao.insertComment(comment) == 1;
	}

	//댓글 수정 
	@Override
	public boolean updateComment(Comment comment) {
		return commentDao.updateComment(comment) == 1;
	}

	//댓글 삭제 
	@Override
	public boolean deleteComment(int commentNo, int userNo) {
		return commentDao.deleteComment(commentNo, userNo) == 1;
	}

	//마이페이지에서 댓글 조회 
	@Override
	public List<Comment> getCommentsByUser(int userNo) {
		return commentDao.selectCommentsByUser(userNo);
	}

	//게시글에서 댓글 조회 
	@Override
	public List<CommentResponse> getCommentsByBoard(int boardNo) {
		return commentDao.selectCommentsByBoard(boardNo);
	}

}
