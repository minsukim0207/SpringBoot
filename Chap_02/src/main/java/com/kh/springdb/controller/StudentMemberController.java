package com.kh.springdb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.service.StudentMemberService;
import com.kh.springdb.vo.StudentMember;

@Controller
@RequestMapping("/members")
public class StudentMemberController {

	private final StudentMemberService studentMemberService;
	
	@Autowired
	public StudentMemberController(StudentMemberService studentMemberService) {
		this.studentMemberService = studentMemberService;
	}
	
	// 전체조회
	@GetMapping
	public String getAllMember(Model model) {
		model.addAttribute("members", studentMemberService.getAllMember());
		return "memberList";
	}
	
	// 추가
	@GetMapping("/new")
	public String showMemberForm(Model model) {
		model.addAttribute("member", new StudentMember());
		return "memberForm";
	}
	
	@PostMapping("/save")
	public String saveMember(@ModelAttribute StudentMember studentMember) {
		studentMemberService.saveMember(studentMember);
		return "redirect:/members";
	}
	
	// 수정
	@GetMapping("/update/{memberId}")
	public String updateMember(@PathVariable Long id, Model model) {
		Optional<StudentMember> studentMember = studentMemberService.getStudentMemberById(id);
		studentMember.ifPresent(value -> model.addAttribute("member", value));
		return "memberForm";
	}
	
	// 삭제
	@GetMapping("/delete/{memberId}")
	public String deleteMember(@PathVariable Long id, Model model) {
		studentMemberService.deleteMemberById(id);
		return "redirect:/members";
	}
}
