package com.kh.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.board.service.BoardService;
import com.kh.board.vo.Board;

@Controller
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping
	public String boardList(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "board_list";
	}
	
	@GetMapping("/detail/{boardId}")
	public String getBoard(@PathVariable Long boardId, Model model) {
		Optional<Board> board = boardService.getBoardById(boardId);
		board.ifPresent(value -> model.addAttribute("board", value));
		return "board_detail";
	}
	
	// insert GetMapping
	@GetMapping("/new")
	public String displayBoardForm(Model model) {
		model.addAttribute("board", new Board());
		return "board_form";
	}
	
	// insert PostMapping
	@PostMapping("/save")
	public String saveBoard(@ModelAttribute Board board) {
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	
	// update
	@GetMapping("/update/{boardId}")
	public String getUpdateBoard(@PathVariable Long boardId, Model model) {
		Optional<Board> board = boardService.getBoardById(boardId);
		board.ifPresent(value -> model.addAttribute("board", value));
		return "board_form";
	}
	
	// delete
	@GetMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable Long boardId, Model model) {
		boardService.deleteBoard(boardId);
		return "redirect:/board_list";
	}
	
	// delete all
	@GetMapping("/delete/all")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
	
	// 특정 키워드 검색
	@GetMapping("/search")
	public String searchBoards(@RequestParam String keyword, Model model) {
		List<Board> boards = boardService.findBoardsByTitle(keyword);	// 특정 키워드 포함해서 게시물 검색
		model.addAttribute("boards", boards);
		return "search-result";
	}
}

/*
@RequestParam : Spring프레임워크에서 클라이언트로부터 전송된 http 요청의 파라미터 값을 받아오기 위해 사용되는 어노테이션
				주로 웹 요청에서 쿼리 파라미터나 폼 데이터를 추출하는데 사용
				클라이언트가 전송한 요청의 파라미터 값을 메서드의 매개변수로 받아올 때 사용
	ex)
	@GetMapping("/ex")
	public String paramMethod(@RequestParam String name, @RequestParam int age) {	// name과 age는 클라이언트가 전송한 요청의 파라미터 값
		
		return "View";
	}
*/