package com.kh.oracledb.mallboard.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
	private int id;
	@Column(unique=true)
	private String userName;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String role;	// 관리자 역할 확인
	private int coin;
	
	@OneToMany(mappedBy="seller")
	private List<Item> items = new ArrayList<>();
	
	// 장바구니
	@OneToMany(mappedBy="user")
	private Cart cart;
	
	// 주문
	@OneToMany(mappedBy="user")
	private List<Order> UserOrder = new ArrayList<>();
	
	// 주문 상품 리스트
	@OneToMany(mappedBy="user")
	private List<OrderItem> UserOrderItem = new ArrayList<>();
	
	// 판매자 상품 리스트
	@OneToMany(mappedBy="seller")
	private List<SaleItem> sellerSaleItem = new ArrayList<>();
	
	// 판매 리스트
	@OneToMany(mappedBy="seller")
	private List<Sale> sellerSale;
	
	// 주문날짜와 판매날짜
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private LocalDate createDate;
	
	// 날짜 값 미리 생성
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
}
