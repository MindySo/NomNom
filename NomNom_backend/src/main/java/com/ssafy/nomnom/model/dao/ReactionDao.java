package com.ssafy.nomnom.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.community.Reaction;

@Repository
public interface ReactionDao {
	//좋아요 등록
    int insertReaction(Reaction reaction);
    
    //좋아요 취소 
    int deleteReaction(@Param("boardNo") int boardNo, @Param("userNo") int userNo);
    
    //좋아요 카운트 
    int countReactionsByBoard(int boardNo);
    
    //유저가 좋아요 눌렀는지 
    boolean hasUserReacted(@Param("boardNo") int boardNo, @Param("userNo") int userNo);
}
