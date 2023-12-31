package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.Board;
import com.kh.springdb.service.BoardService;

@Controller
@RequestMapping("/boards")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public String getAllBoards(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "board-list";
	}

	@GetMapping("/{boardId}")
	public String getBoardById(@PathVariable int boardId, Model model) {
		Board board = boardService.getBoardById(boardId);
		model.addAttribute("board", board);
		return "board-detail";
	}
	
	// GetMapping을 사용해서 게시글 작성하는 HTML로 이동한 후
	@GetMapping("/create")
	public String displayCreateForm(Model model) {	// Model 객체를 매개변수로 받아서 templates(view)로 데이터를 전달할 수 있음
		model.addAttribute("board", new Board()); // new Board로 새로운 Board 객체를 생성해서 모델에 추가
		return "board-form";
	}
	
	// PostMapping을 사용해서 작성해놓은 insert HTML form을 가져온다
	@PostMapping("/create")
	public String createBoard(@ModelAttribute Board board) {
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	
	@GetMapping("/update/{boardId}")
	public String displayUpdateForm(@PathVariable int boardId, Model model) {
		Board board = boardService.getBoardById(boardId);
		model.addAttribute("board", board);
		return "board-form";
	}
	
	@PostMapping("/update/{boardId}")
	public String displayUpdateForm(@PathVariable int boardId, @ModelAttribute Board board) {
		board.setBoardId(boardId);
		boardService.updateBoard(board);
		return "redirect:/boards";
	}
	
	@GetMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable int boardId) {
		boardService.deleteBoard(boardId);
		return "redirect:/boards";
	}
	
	@GetMapping("/delete-all-boards")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
}
