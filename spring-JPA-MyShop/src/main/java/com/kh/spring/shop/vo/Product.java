package com.kh.spring.shop.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="productId_seq")
	@SequenceGenerator(name="productId_seq", sequenceName="productId_seq", allocationSize=1)
	private Long id;
	private String productName;
	private String category;
	private String price;
	private String stockQuantity;
	private String description;
}
