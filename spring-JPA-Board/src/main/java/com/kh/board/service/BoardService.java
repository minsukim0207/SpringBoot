package com.kh.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.board.repository.BoardRepository;
import com.kh.board.vo.Board;

@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> getAllBoards() {
		return boardRepository.findAll();
	}
	
	public Optional<Board> getBoardById(Long id) {
		return boardRepository.findById(id);
	}
	
	// 게시물 추가
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}
	
	// 삭제
	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}
	
	// 전체 삭제
	public void deleteAllBoards() {
		boardRepository.deleteAll();
	}
}
