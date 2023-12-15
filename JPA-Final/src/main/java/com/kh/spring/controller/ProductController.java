package com.kh.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.model.Product;
import com.kh.spring.service.CommentService;
import com.kh.spring.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	private CommentService commentService;
	
	@GetMapping("/")
	public String mainPage(Model model) {
		return "index";
	}
	
	// 페이지네이션 체크
	@GetMapping("/list")
	public String pageList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		// @RequestParam(value="page", defaultValue="0")
		// 어떤 값을 가지고 요청을 할 지 지정하기 위해 @RequestParam 이용
		// value="page" 값으로 page 이름을 받기로 지정
		// 만약 초반이나 후반에 어떤 값이 page 안에 없다면 page가 null 값이라면 기본값으로 0으로 설정해서 초기값을 null이 아닌 0으로 처리하겠다
		// 페이지는 배열값으로 0이지만 변수에는 추후 1이 할당될 예정
		// 페이지에는 1로 표기가 되지만 코드안에서는 0부터 시작하는 것으로 표기를 해줘야 함
		Page<Product> paging = productService.getList(page);
		model.addAttribute("paging", paging);
		return "product_List";
	}
	
	// 상품 전체 목록 페이지
	//@GetMapping("/list")
	public String productList(Model model) {
		// 아이템에 추가한 서비스를 불러와서 모델에 넣어주기
		List<Product> products = productService.allProductView();
		model.addAttribute("products", products);
		return "productList";
	}
	
	// 상품 등록 페이지
	@GetMapping("/new")
	public String productSaveForm(Model model) {
		model.addAttribute("product", new Product());
		return "addProductForm";
	}
	
	@PostMapping("/new")
	public String productSave(Product product, MultipartFile imgFile) throws IllegalStateException, IOException {
		productService.saveProduct(product, imgFile);
		return "redirect:/";
	}
	
	// 상품 상세보기 페이지
	@GetMapping("/{id}")
	public String productDetail(@PathVariable("id") int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "productDetail";
	}
	
	// 댓글 작성
	@PostMapping("/addComment")
	public String addComment(@RequestParam int productId, @RequestParam String commentContent) {
		commentService.addComment(productId, commentContent);
		return "redirect:/product/detail/";
	}
	
	// 좋아요 한 내용 받아줄 수 있게 PostMapping
	@PostMapping
	public String likeProduct(int productId) {
		productService.likeProduct(productId);
		return "redirect:/list";
	}
}
