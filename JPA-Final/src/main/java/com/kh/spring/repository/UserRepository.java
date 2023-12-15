package com.kh.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

	// 로그인을 위해 검색
	Optional<SiteUser> findByUsername(String username);
}
