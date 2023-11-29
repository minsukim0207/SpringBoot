package com.kh.springdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.shop.service.ProductService;
import com.kh.springdb.vo.Product;

@Controller
@RequestMapping("/produts")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public String getAllProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "product_list";
	}
	
	@GetMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		// Optional 안에는 productService.getProductById(id)로 id 값을 가져와서 id에 해당하는 제품을 가지고 옴
		// 만약 id에 해당하는 제품이 존재하지 않는다면 Optional은 비어있게 됨
		// Optional이 비어있게 되면 에러가 발생할 수 있으므로, 추후 비어있을 경우를 대비해서 예외값을 처리해주는 것이 좋음
		// 예외값 처리 방법: orElse를 이용해서 대체값을 제공하거나 페이지 이동 처리를 할 수 있음 ex: error.html 사용
		//				 orElseGet : 대체값을 생성하는 함수 제공
		//				 orElseThrow : 예외 던짐
		product.ifPresent(value -> model.addAttribute("product", value));
		// ifPresent : Optional 객체 안에 값이 존재할 경우 람다식 표현을 실행하기 위한 메서드
		// 				value 값이 존재하면 모델에 product 변수명을 사용해서 product 안에 value 값을 추가
		//				추가된 product는 html 템플릿 안에서 product를 타임리프를 통해 호출해서 value값을 사용할 수 있음
		return "product_form";
	}
	
	@GetMapping("/new")
	public String showProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "product_form";
	}

}
