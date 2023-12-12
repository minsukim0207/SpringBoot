package com.kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.model.Comment;
import com.kh.spring.model.Product;
import com.kh.spring.repository.CommentRepository;
import com.kh.spring.repository.ProductRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	// 댓글 추가
	public Comment addComment(int productId, String content) {
		Product product = productRepository.findById(productId).orElse(null);
		// 만약 상품이 존재하지 않을 경우 댓글 또한 존재하지 않음
		if (product == null) {
			throw new RuntimeException("상품 미존재");
		}
		// 댓글 생성을 위한 생성자
		Comment comment = new Comment();
		comment.setProduct(product);
		comment.setContent(content);
		return commentRepository.save(comment);
	}
}
