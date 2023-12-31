package com.kh.springdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.springdb.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired	// BoardService boardService = new BoardService(); 에서 new BoardService()를 대신 만들어줌
	private ProductService productService;
	
	@GetMapping("/productLists")
	public String displayProductList(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "productLists";
	}
}
