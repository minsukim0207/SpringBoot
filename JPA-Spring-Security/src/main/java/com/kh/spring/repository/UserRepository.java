package com.kh.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.SecurityUser;

public interface UserRepository extends JpaRepository<SecurityUser, Long> {

	// 로그인을 위한 옵션 활용 repo 생성
	Optional<SecurityUser> findByUsername(String userName);
}
