package com.ssafy.nomnom.model.dto.community;

import java.time.LocalDateTime;

public class CommentResponse {
	private String userId;
	private String commentContent;
    private LocalDateTime commentRegDate;

	public CommentResponse() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Override
	public String toString() {
		return "CommentResponse [userId=" + userId + ", commentContent=" + commentContent + "]";
	}
	
	
}
