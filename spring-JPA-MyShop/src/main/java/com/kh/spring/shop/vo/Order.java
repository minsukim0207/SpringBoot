package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orderId_seq")
	@SequenceGenerator(name="orderId_seq", sequenceName="orderId_seq", allocationSize=1)
	private Long id;	// 주문 번호
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;	// 주문한 상품
	private int quantity;	// 제품 수량
}

/*
 * @JoinColumn(name="조인하고자하는컬럼명") 외래키(Foreign Key)
 * 데이터베이스에 테이블로 존재하는 객체를 작성
 */
