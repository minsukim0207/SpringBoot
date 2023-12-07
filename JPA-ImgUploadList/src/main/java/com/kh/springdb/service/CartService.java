package com.kh.springdb.service;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.Cart;
import com.kh.springdb.model.CartItem;
import com.kh.springdb.model.Order;
import com.kh.springdb.repository.CartItemRepository;
import com.kh.springdb.repository.CartRepository;
import com.kh.springdb.repository.ItemRepository;
import com.kh.springdb.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
//@RequiredArgsConstructor
public class CartService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;
	
	public List<CartItem> findCartItemByCartId(int cartId) {
		return cartItemRepository.findCartItemByItemId(cartId);
	}

	//findByItemId
	public List<CartItem> findByItemId(int itemId) {
		return cartItemRepository.findByItemId(itemId);
	}
	
	public Cart getCartById(Long cartId) {
		return cartRepository.findById(cartId).orElse(null);
	}
	
	@Transactional
	public void addCart(Long cartId, Item newItem, int amount) {
		
		// 장바구니 없으면 생성
		Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
			Cart newCart = new Cart();
			return cartRepository.save(newCart);
		});
		
		// 장바구니에 해당 아이템 있는지 확인
		CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cartId, newItem.getId());
		
		if (cartItem == null) {
			// 없으면 새로운 CartItem 생성
			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setItem(newItem);
			cartItem.setCount(amount);
		} else {
			// 있으면 수량만 증가
			cartItem.addCount(amount);
		}
		
		// 생성 또는 업데이트된 CartItem 저장
		cartItemRepository.save(cartItem);
	}
	
	// 결제하기
	@Transactional
	public void checkOut(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElse(null);
		
		// 카트가 null이 아닐때
		if (cart != null) {
			Order order = Order.builder().cart(cart).build();	// Order + cart(cart) = build()
			
			orderRepository.save(order);
			
			// delete clear
			cart.getCartItems().clear();
			cartRepository.save(null);
		}
	}
}
