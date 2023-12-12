package com.kh.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
