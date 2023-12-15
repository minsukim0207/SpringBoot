package com.kh.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.model.UserCreateForm;
import com.kh.spring.model.UserRole;
import com.kh.spring.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
// 공통으로 들어가는 url이 있다면 RequestMapping 사용해서 user로 묶어주기
public class UserController {
	private final UserService userService;
	// 회원가입 창
	@GetMapping("/signup")
	public String signUp(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	
	// 회원가입에 대한 PostMapping
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		// 패스워드 일치 확인
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호와 비밀번화 확인 불일치");
		}

		// html 폼에서 선택한 역할을 가지고 오기 위해
		UserRole role = userCreateForm.getIsRole();
		userService.createUser(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1(), role);

		//userService.createUser(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());
		
		return "redirct:/";
	}


	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}
