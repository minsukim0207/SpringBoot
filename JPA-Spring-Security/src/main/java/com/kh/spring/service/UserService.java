package com.kh.spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring.model.SecurityUser;
import com.kh.spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입 시 비밀번호를 암호화해서 DB에 저장
	public SecurityUser create(String userName, String email, String password) {
		SecurityUser user = new SecurityUser();
		user.setUsername(userName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));	// passwordEncoder 사용해서 입력받은 비밀번호를 암호화 처리
		this.userRepository.save(user);
		return user;
	}

}
