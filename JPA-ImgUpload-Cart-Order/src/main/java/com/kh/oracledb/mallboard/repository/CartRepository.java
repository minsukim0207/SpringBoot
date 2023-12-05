package com.kh.oracledb.mallboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracledb.mallboard.vo.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	Cart findByUserId(int id);
	
	Cart findCartById(int id);
	
	Cart findCartByUserId(int id);

}
