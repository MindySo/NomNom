package com.ssafy.nomnom.model.dto.community;

public class BoardSearchCondition {
	private String boardType; //null 또는 ""이면 전체 
	private String keyword; //제목 + 내용 통합 검색 
	
	public BoardSearchCondition() {}

	public BoardSearchCondition(String boardType, String keyword) {
		super();
		this.boardType = boardType;
		this.keyword = keyword;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
