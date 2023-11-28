package com.kh.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.springdb.vo.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	// 메서드 이름으로 간단하게 쿼리를 정의하기
	Product findByProductName(String product_name);
	
	// 전체 제품 출력 메서드
	List<Product> findByPriceDesc(Integer price);
	
	// 만약 Repository에 쿼리를 추가하고 싶을 경우
	@Query("SELECT * FROM ProductItem WHERE price")
	List<Product> findByDetail(@Param("category") String category);
	
	/*
	public void createProductList() {
		Product product = new Product();
		product.setProduct_name("text product");
		product.setPrice(1000);
		product.setCategory("가전");
	}
	*/
}

/*
Repository
Spring Data JPA에서 제공하는 인터페이스
데이터베이스에서 User 엔티티에 접근하는데 사용
기본적인 CRUD 작업을 수행할 수 있는 메서드를 제공
ex)
	조회 : 전체조회(findAll), 단일 조회(findById)
	저장 : save
	삭제 : deleteById
*/