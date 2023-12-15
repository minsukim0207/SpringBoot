package com.kh.spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring.model.SiteUser;
import com.kh.spring.model.UserRole;
import com.kh.spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입할 경우 계정 생성을 위해 service를 만들어줌
	// 기존 서비스에서 했던 회원가입과 조금 다른 점은 비밀번호를 암호화 처리해서 저장해주는 것이 조금 다름
	public SiteUser createUser(String username, String email, String password, UserRole role) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		// 사용자 역할 설정
		user.setIsRole(role);
		userRepository.save(user);
		return user;
	}
}
