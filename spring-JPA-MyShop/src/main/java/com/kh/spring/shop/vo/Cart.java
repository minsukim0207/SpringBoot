package com.kh.spring.shop.vo;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	// 장바구니에 담긴 CartItem들을 저장하는 목록
	private List<CartItem> items = new ArrayList<>();
	
	// 장바구니에 담긴 목록을 반환
	public List<CartItem> getItems() {
		return items;
	}

	// 장바구니에 제품을 추가하는 메서드
	public void addToCart(Product product, int quantity) {
		for (CartItem item : items) {
			if (item.getProduct().getId().equals(product.getId())) {
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}
		CartItem newItem = new CartItem(product, quantity);
		items.add(newItem);
	}
}
