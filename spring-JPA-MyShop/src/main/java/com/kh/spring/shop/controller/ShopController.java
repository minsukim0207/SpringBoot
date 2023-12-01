package com.kh.spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.spring.shop.service.ShopService;
import com.kh.spring.shop.vo.Product;

public class ShopController {

	@Autowired
	private ShopService shopService;
	
	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}
	
	// 주문 관련 내용 처리
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Product product, int quantity, Model model) {
		shopService.placeOrder(product, quantity);
		model.addAttribute("msg", "주문 성공");
		return "redirect:/order-check";
	}
	
	@PostMapping("/payment-finish")
	public String processPayment(Long orderId, String paymentStatus, Model model) {
		shopService.savePayment(orderId, paymentStatus);
		model.addAttribute("msg", "결제 성공");
		return "redirect:/payment-check";
	}
}

/*
@RequestBody : 정보를 url이 아니라 자바 객체로 받음
				요청했던 body에 위치하도록 할 때 사용
@RequestParam : 정보를 url에 저장
*/