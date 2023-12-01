package com.kh.spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.shop.service.ProductService;
import com.kh.spring.shop.vo.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

		@Autowired
		private ProductService productService;
		
		@GetMapping("/new")
		public String joinMember(Model model) {
			model.addAttribute("product", new Product());
			return "product/product-form";
		}
		
		@PostMapping("/new")
		public String addProduct(@ModelAttribute Product product) {
			productService.saveProduct(product);
			return "redirect:/product-list";
		}
		
		@GetMapping("product-list")
		public String productList() {
			return "products/product-list";
		}
	
}
