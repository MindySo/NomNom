package com.ssafy.nomnom.model.service.community;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dto.community.Board;
import com.ssafy.nomnom.model.dto.community.BoardSearchCondition;

public interface BoardService {

    // 전체 게시글 조회
    List<Board> getAllBoards();

    // 단일 게시글 조회
    Board getBoardById(int boardNo);
    
    //유저별 조회
    List<Board> getBoardsByUser(int userNo);
    
    //카테고리별 조회 
    List<Board> getBoardsByType(String boardType);
    
    // 게시글 등록
    boolean createBoard(Board board, MultipartFile file);

    // 게시글 수정
    boolean updateBoard(Board board, MultipartFile file);

    // 게시글 삭제
    boolean deleteBoard(int boardNo, int userNo);
    
    //검색 
    List<Board> searchBoards(BoardSearchCondition cond);
}
