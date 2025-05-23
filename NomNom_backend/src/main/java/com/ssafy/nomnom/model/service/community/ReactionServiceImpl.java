package com.ssafy.nomnom.model.service.community;

import org.springframework.stereotype.Service;

import com.ssafy.nomnom.model.dao.ReactionDao;
import com.ssafy.nomnom.model.dto.community.Reaction;

@Service
public class ReactionServiceImpl implements ReactionService {

	private final ReactionDao reactionDao;
	public ReactionServiceImpl(ReactionDao reactionDao) {
		this.reactionDao = reactionDao;
	}
	
	//좋아요 등록과 취소 
    @Override
    public boolean toggleReaction(int boardNo, int userNo) {
        boolean alreadyLiked = reactionDao.hasUserReacted(boardNo, userNo);
        if (alreadyLiked) {
            reactionDao.deleteReaction(boardNo, userNo);
            return false; // 취소됨
        } else {
            reactionDao.insertReaction(new Reaction(boardNo, userNo));
            return true; // 등록됨
        }
    }

    //좋아요 카운트 
    @Override
    public int getReactionCount(int boardNo) {
        return reactionDao.countReactionsByBoard(boardNo);
    }

    //유저가 좋아요 눌렀는지 
    @Override
    public boolean hasUserReacted(int boardNo, int userNo) {
        return reactionDao.hasUserReacted(boardNo, userNo);
    }

}
