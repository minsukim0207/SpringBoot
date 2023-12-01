package com.kh.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
}
