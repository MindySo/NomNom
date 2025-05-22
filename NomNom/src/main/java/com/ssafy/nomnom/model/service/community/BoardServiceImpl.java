package com.ssafy.nomnom.model.service.community;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dao.AttachmentDao;
import com.ssafy.nomnom.model.dao.BoardDao;
import com.ssafy.nomnom.model.dto.attachment.Attachment;
import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;
import com.ssafy.nomnom.model.dto.community.Board;
import com.ssafy.nomnom.model.dto.community.BoardSearchCondition;
import com.ssafy.nomnom.model.service.attachment.AttachmentService;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;
	private final AttachmentService attachmentService;
	private final AttachmentDao attachmentDao;

	public BoardServiceImpl(BoardDao boardDao, AttachmentService attachmentService, AttachmentDao attachmentDao) {
		this.boardDao = boardDao;
		this.attachmentService = attachmentService;
		this.attachmentDao = attachmentDao;
	}

	// 전체 게시글 조회
	@Override
	public List<Board> getAllBoards() {
		return boardDao.selectAllBoards();
	}

	// 단일 게시글 조회
	@Override
	public Board getBoardById(int boardNo) {
		return boardDao.selectBoardById(boardNo);
	}
	
	//유저별 조회 
    @Override
    public List<Board> getBoardsByUser(int userNo) {
        return boardDao.selectBoardsByUser(userNo);
    }

    //카테고리별 조회 
    @Override
    public List<Board> getBoardsByType(String boardType) {
        return boardDao.selectBoardsByType(boardType);
    }

	// 게시글 등록
	@Override
    @Transactional
    public boolean createBoard(Board board, MultipartFile file) {
        boolean result = boardDao.insertBoard(board) == 1;
        if (result && file != null && !file.isEmpty()) {
            attachmentService.writeAttachment(file, AttachmentTargetEnum.BOARD, board.getBoardNo());
        }
        return result;
	}

	//게시글 수정 
	@Override
	@Transactional
	public boolean updateBoard(Board board, MultipartFile file) {
		boolean result = boardDao.updateBoard(board) == 1;
		if (result) {
			// 기존 첨부파일 삭제
			Attachment condition = new Attachment();
			condition.setAttachmentTarget(AttachmentTargetEnum.BOARD);
			condition.setTargetNo(board.getBoardNo());
			attachmentDao.deleteAttachmentByTargetAndNo(condition);

			// 새 파일 저장
			if (file != null && !file.isEmpty()) {
				attachmentService.writeAttachment(file, AttachmentTargetEnum.BOARD, board.getBoardNo());
			}
		}
		return result;
	}

	// 게시글 삭제
	@Override
	public boolean deleteBoard(int boardNo, int userNo) {
		return boardDao.deleteBoard(boardNo, userNo) == 1;
	}

	//검색 
	@Override
	public List<Board> searchBoards(BoardSearchCondition cond) {
		return boardDao.searchBoards(cond);
	}
}
