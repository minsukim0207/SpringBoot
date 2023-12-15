package com.kh.spring.model;

import lombok.Getter;

@Getter
public enum UserRole {

	// admin 관리자
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	// 유저가 어떤 롤인지 값을 가져오기위해 value 추가
	private String value;

	// 로그인 시 ADMIN인지 USER인지 지정
	UserRole(String value) {
		this.value = value;
	}
}
