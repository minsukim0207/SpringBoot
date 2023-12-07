package com.kh.springdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	List<CartItem> findCartItemByItemId(int id);
	CartItem findCartItemById(int id);
	
	List<CartItem> findByItemId(int itemId);
	
	CartItem findByCartIdAndItemId(Long cartId, int itemId);

}
