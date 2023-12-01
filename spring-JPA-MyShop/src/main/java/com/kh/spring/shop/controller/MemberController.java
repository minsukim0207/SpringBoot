package com.kh.spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.shop.service.MemberService;
import com.kh.spring.shop.vo.Member;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	/*
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	*/
	
	// 회원가입 후 결과 확인
	@GetMapping("/new")
	public String joinMember(Model model) {
		model.addAttribute("member", new Member());
		return "member/join-form";
	}
	
	@PostMapping("/new")
	public String addMember(@ModelAttribute Member member) {
		// 회원 존재 여부 확인
		memberService.saveMember(member);
		return "redirect:/";
	}
	
	// 로그인 시도 후 실패 시
	@GetMapping("/login")
	public String loginMember() {
		return "member/member-loginform";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", true);
		return "member/member-loginform";
	}
}
