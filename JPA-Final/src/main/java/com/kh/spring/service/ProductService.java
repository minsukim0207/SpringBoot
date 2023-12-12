package com.kh.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.model.Product;
import com.kh.spring.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	public List<Product> allProductView() {
		return productRepository.findAll();
	}
	
	// pagination add
	public Page<Product> getList(int page) {
		Pageable pageable = PageRequest.of(page, 1);	// 페이지 값, 페이지당 보여줄 개수
		return productRepository.findAll(pageable);
	}
	
	public void saveProduct(Product product, MultipartFile imgFile) throws IllegalStateException, IOException {
		
		// 이미지 파일 이름 가져오기
		String originName = imgFile.getOriginalFilename();
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img";
		File saveFile = new File(projectPath, originName);
		imgFile.transferTo(saveFile);
		product.setImgName(originName);	// 파일 이름 원본 저장
		product.setImgPath(projectPath);	// DB에 파일 경로 저장
		productRepository.save(product);
	}
	
	// 상세 페이지나 수정하기를 위해 id로 가져오는 메서드
	public Product getProductById(int id) {
		return productRepository.findProductById(id);
	}
}
