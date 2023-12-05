package com.kh.oracledb.mallboard.vo;

import java.util.ArrayList;
import java.util.List;

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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_seq")
	@SequenceGenerator(name="item_seq", sequenceName="item_seq", allocationSize=1)
	private int id;	// primary key
	private String name;
	private String description;
	private int price;
	private int count;
	private int stockQuantity;
	private boolean isSale;
	//@ManyToOne	// 판매자 : 판매상품 = N : 1
	//@JoinColumn(name="admin_id")
	//private Admin admin;	// 판매자 아이디
	private String photo;
	private String photoName;
	private String photoPath;
	private List<CartItem> cartItems = new ArrayList<>();
}
