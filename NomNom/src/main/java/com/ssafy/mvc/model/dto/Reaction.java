package com.ssafy.mvc.model.dto;

public class Reaction {
	private Integer reactionNo;
    private Integer boardNo;
    private Integer userNo;
    
    
	public Reaction() {
	}
	
	
	public Reaction(Integer reactionNo, Integer boardNo, Integer userNo) {
		this.reactionNo = reactionNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
	}
	
	
	public Integer getReactionNo() {
		return reactionNo;
	}
	public void setReactionNo(Integer reactionNo) {
		this.reactionNo = reactionNo;
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
	@Override
	public String toString() {
		return "Reaction [reactionNo=" + reactionNo + ", boardNo=" + boardNo + ", userNo=" + userNo + "]";
	}
}
