package com.kh.springdb.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
*/
public class User {
	/*
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
	private int id;
	@Column(unique=true)
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String role;
	private int coin;
	// 판매자 상품
	private List<Item> items = new ArrayList<>();
	
	// 판매자 판매 내역
	//@OneToMany(mappedBy="seller")
	//private List<Sale> sellerSale;

	// 판매자 판매 상품
	//@OneToMany(mappedBy="seller")
	//private List<SaleItem> sellerSaleItem = new ArrayList<>();
	 */
}
