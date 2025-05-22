package com.ssafy.nomnom.model.service.community;

public interface ReactionService {
    /**
     * 게시글 좋아요 토글 (등록 or 취소)
     * @return true = 등록, false = 취소
     */
	
	//좋아요 등록, 취소 
    boolean toggleReaction(int boardNo, int userNo);
    
    //좋아요 카운트 
    int getReactionCount(int boardNo);
    
    //유저가 좋아요 눌렀는지 
    boolean hasUserReacted(int boardNo, int userNo);
}

