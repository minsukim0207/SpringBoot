package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.UserMapper;
import com.kh.springdb.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	// 전체 회원 조회
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	
	// 한 회원 조회
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}
	
	// 회원 저장
	public void registerUser(User user) {
		userMapper.insertUser(user);
	}
}
