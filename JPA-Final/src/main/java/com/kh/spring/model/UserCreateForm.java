package com.kh.spring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@NotNull(message="가입자선택 필수")
	private UserRole isRole;
	
	@Size(min=3, max=25)
	@NotEmpty(message="사용자 아이디 입력 필수")
	private String username;
	
	@NotEmpty(message="사용자 비밀번호 입력 필수")
	private String password1;
	
	@NotEmpty(message="비밀번호 확인 입력 필수")
	private String password2;

	@Email
	@NotEmpty(message="사용자 이메일 입력 필수")
	private String email;
}

/*
@NotNull : 입력값이 null일 경우 메시지가 나올 수 있도록 표기 -> Null 체크 여부
@NotEmpty : 메시지를 예외값으로 발생 -> Empty 예외 체크
*/