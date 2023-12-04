package com.kh.springdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spingdb.repository.StudentMemberRepository;
import com.kh.springdb.vo.StudentMember;

import jakarta.transaction.Transactional;

@Service
public class StudentMemberService {
	
	private final StudentMemberRepository studentMemberRepository;

	@Autowired
	public StudentMemberService(StudentMemberRepository studentMemberRepository) {
		this.studentMemberRepository = studentMemberRepository;
	}
	
	// 전체 조회
	public List<StudentMember> getAllMember() {
		return studentMemberRepository.findAll();
	}
	
	// 저장
	@Transactional
	public StudentMember saveMember(StudentMember studentMember) {
		return studentMemberRepository.save(studentMember);
	}
	
	// 삭제
	public void deleteMemberById(Long id) {
		studentMemberRepository.deleteById(id);
	}
	
	// id로 하나 조회
	public Optional<StudentMember> getStudentMemberById(Long id) {
		return studentMemberRepository.findById(id);
	}
}
