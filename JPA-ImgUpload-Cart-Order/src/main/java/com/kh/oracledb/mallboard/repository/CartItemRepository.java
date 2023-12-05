package com.kh.oracledb.mallboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracledb.mallboard.vo.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	CartItem findByCartIdAndItemId(int cartId, int itemId);
	
	CartItem findCartItemById(int id);
	
	List<CartItem> findCartItemByItemId(int id);
}
