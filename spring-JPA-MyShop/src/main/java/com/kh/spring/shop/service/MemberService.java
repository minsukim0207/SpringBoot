package com.kh.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.MemberRepository;
import com.kh.spring.shop.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	/*
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	*/
	
	// 회원가입
	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}
	
	// 중복 확인
	public void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		// 존재하는 회원이면 Exception을 이용하여 이미 존재함을 표시
		
		
		// DB에 이미 가입된 이메일이 존재한다면 예외를 발생시키는 if문
		//if (findMember != null) {
		//	throw new IllegalStateException("이미 가입된 회원");
		//}
	}
}

/* UserDetailsService
사용자 정보를 가지고 오거나 인증할 때 사용하는 서비스
사용자의 아이디나 이메일을 받아와서 받아온 정보를 객체로 반환할 때 사용
*/