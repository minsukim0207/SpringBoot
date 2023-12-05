package com.kh.oracledb.mallboard.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.oracledb.mallboard.service.ItemService;
import com.kh.oracledb.mallboard.vo.Item;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// @NotNull로 표시된 필드를 사용해서 생성자 생성
public class ItemController {

	private final ItemService itemService;
	
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Item> items = itemService.allItemList();
		model.addAttribute("items", items);
		return "index";
	}
	
	@GetMapping("/itemList")
	public String showItemList(Model model) {
		List<Item> items = itemService.allItemList();
		model.addAttribute("items", items);
		return "itemList";
	}
	
	/*
	@GetMapping("/item/list")
	public String itemList(Model model, @PageableDefault(size=12) Pageable pageable, @RequestParam(name="keyword", required=false) String keyword) {
		// 페이지네이션 처리
		Page<Item> items = itemService.getItemById(pageable);
		return "itemList";
	}
	*/
	
	@GetMapping("/new")
	public String addItemForm() {
		return "addItemForm";
	}
	
	@PostMapping("/save")
	public String saveItem(Item item, MultipartFile photoFile) {	// 이미지 파일도 같이 등록될 수 있도록 파라미터 생성
		itemService.saveItem(item, photoFile);
		return "redirect:/itemList";
	}
	
	@GetMapping("/view/{id}")
	public String viewItem(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("item", itemService.getItemById(id));
		return "viewItem";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Integer id) {
		itemService.itemDelete(id);
		return "itemList";
	}
}
