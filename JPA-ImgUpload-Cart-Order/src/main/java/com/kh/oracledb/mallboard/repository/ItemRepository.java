package com.kh.oracledb.mallboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracledb.mallboard.vo.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	Item findItemById(int id);
	
	Page<Item> findByNameContaining(String keyword, Pageable pageable);
}

/*
Page : 페이지 당 데이터를 나타내는 클래스

Pageable : 인터페이스
*/