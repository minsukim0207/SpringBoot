package com.kh.oracledb.mallboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.oracledb.mallboard.vo.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUserName(String username);
	
	User findById(int id);

}
