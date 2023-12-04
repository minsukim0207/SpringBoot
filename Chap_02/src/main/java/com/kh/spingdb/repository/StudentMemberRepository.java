package com.kh.spingdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.vo.StudentMember;

public interface StudentMemberRepository extends JpaRepository<StudentMember, Long> {

}
