package com.ssafy.nomnom.model.dto.board;

import java.time.LocalDateTime;

public class Board {
	 private Integer boardNo;
	    private String boardType;
	    private Integer userNo;
	    private String boardTitle;
	    private String boardContent;
	    private LocalDateTime boardRegDate;
	    private Integer boardViewCnt;
	    
	    
		public Board() {
		}
		
		
		public Board(Integer boardNo, String boardType, Integer userNo, String boardTitle, String boardContent,
				LocalDateTime boardRegDate, Integer boardViewCnt) {
			this.boardNo = boardNo;
			this.boardType = boardType;
			this.userNo = userNo;
			this.boardTitle = boardTitle;
			this.boardContent = boardContent;
			this.boardRegDate = boardRegDate;
			this.boardViewCnt = boardViewCnt;
		}
		
		
		public Integer getBoardNo() {
			return boardNo;
		}
		public void setBoardNo(Integer boardNo) {
			this.boardNo = boardNo;
		}
		public String getBoardType() {
			return boardType;
		}
		public void setBoardType(String boardType) {
			this.boardType = boardType;
		}
		public Integer getUserNo() {
			return userNo;
		}
		public void setUserNo(Integer userNo) {
			this.userNo = userNo;
		}
		public String getBoardTitle() {
			return boardTitle;
		}
		public void setBoardTitle(String boardTitle) {
			this.boardTitle = boardTitle;
		}
		public String getBoardContent() {
			return boardContent;
		}
		public void setBoardContent(String boardContent) {
			this.boardContent = boardContent;
		}
		public LocalDateTime getBoardRegDate() {
			return boardRegDate;
		}
		public void setBoardRegDate(LocalDateTime boardRegDate) {
			this.boardRegDate = boardRegDate;
		}
		public Integer getBoardViewCnt() {
			return boardViewCnt;
		}
		public void setBoardViewCnt(Integer boardViewCnt) {
			this.boardViewCnt = boardViewCnt;
		}
		@Override
		public String toString() {
			return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", userNo=" + userNo + ", boardTitle="
					+ boardTitle + ", boardContent=" + boardContent + ", boardRegDate=" + boardRegDate
					+ ", boardViewCnt=" + boardViewCnt + "]";
		}
}
