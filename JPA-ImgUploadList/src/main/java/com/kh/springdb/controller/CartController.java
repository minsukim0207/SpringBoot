package com.kh.springdb.controller;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.springdb.model.Cart;
import com.kh.springdb.service.CartService;
import com.kh.springdb.service.ItemService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;
	private final ItemService itemService;
	
	public CartController(CartService cartService, ItemService itemService) {
		this.cartService = cartService;
		this.itemService = itemService;
	}
	
	@GetMapping
	public String viewCart(Model model) {
		Cart cart = cartService.getCartById(1L);
		model.addAttribute("cart", cart);
		return "cartView";
	}
	
	@GetMapping("/add/{itemId}")
	public String addToCart(@PathVariable int itemId, Model model) {
		Item newItem = itemService.getItemById(itemId);
	
		cartService.addCart(1L,  newItem, 1);	// 임의의 값을 지정해줄 때 1L이라는 표현을 쓰기도 함
		return "redirect:/cart";
		// 1L이란?
		// 
	}
	
	@PostMapping("/add")
	public String addToCartItem(@RequestParam("itemId") Long itemId, Model model) {
		Item newItem = itemService.getItemById(itemId.intValue());
		
		cartService.addCart(1L, newItem, 1);
		
		return "redirect:/cart";
	}
	
	// 결제 완료 후 장바구니 삭제
	@PostMapping("/checkout")
	public String checkOut(RedirectAttributes redirectAttribute) {
		Long cartId = 1L;

		cartService.checkOut(cartId);
		redirectAttribute.addFlashAttribute("checkoutStatus", "success");
	}
}

/*
 * Integer와 int의 차이
 * Integer : wrapper 클래스 객체로 감싸져 있기 때문에 null 값을 가질 수 있음
 * int : java에서 기본 데이터 타입 정수를 나타내는 값. null 값을 가질 수 없음
 */
