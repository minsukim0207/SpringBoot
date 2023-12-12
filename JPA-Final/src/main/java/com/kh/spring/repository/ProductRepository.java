package com.kh.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// 상세보기
	Product findProductById(int id);
	
	// 페이지네이션
	Page<Product> findAll(Pageable pageable);
}
