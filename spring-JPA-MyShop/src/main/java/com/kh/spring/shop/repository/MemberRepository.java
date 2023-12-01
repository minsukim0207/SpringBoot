package com.kh.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.shop.vo.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	// 회원가입
	// findAll
	
	// 이메일로 아이디 검색
	Member findByEmail(String email);
	
	// 로그인
	// findById
	
	// 마이페이지
	
	// 아이디 찾기

}
