package com.ssafy.nomnom.model.dto.board;

import java.time.LocalDateTime;

public class Comment {
	private Integer commentNo;
    private Integer boardNo;
    private Integer userNo;
    private String commentContent;
    private Integer commentParentNo;
    private LocalDateTime commentRegDate;
    
    
	public Comment() {
	}
	
	
	public Comment(Integer commentNo, Integer boardNo, Integer userNo, String commentContent, Integer commentParentNo,
			LocalDateTime commentRegDate) {
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.commentContent = commentContent;
		this.commentParentNo = commentParentNo;
		this.commentRegDate = commentRegDate;
	}
	
	
	public Integer getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getCommentParentNo() {
		return commentParentNo;
	}
	public void setCommentParentNo(Integer commentParentNo) {
		this.commentParentNo = commentParentNo;
	}
	public LocalDateTime getCommentRegDate() {
		return commentRegDate;
	}
	public void setCommentRegDate(LocalDateTime commentRegDate) {
		this.commentRegDate = commentRegDate;
	}
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", boardNo=" + boardNo + ", userNo=" + userNo + ", commentContent="
				+ commentContent + ", commentParentNo=" + commentParentNo + ", commentRegDate=" + commentRegDate + "]";
	}
}
