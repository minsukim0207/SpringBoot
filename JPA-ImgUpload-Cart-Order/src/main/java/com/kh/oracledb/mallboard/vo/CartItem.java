package com.kh.oracledb.mallboard.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cartItem_seq")
	@SequenceGenerator(name="cartItem_seq", sequenceName="cartItem_seq", allocationSize=1)
	private int id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cart_id")
	private Cart cart;
	@JoinColumn(name="item_id")
	private Item item;
	private int cartCount;
}

/*
@ManyToOne(fetch=FetchType.EAGER)
여러 엔티티가 하나의 엔티티에 감싸져서 활용되는 N:1 관계를 나타냄
fetch=FetchType.EAGER : 가져오기, 감싸진(매핑된) 엔티티를 자바에서 검색할 때 바로 로딩해서 가져올 수 있도록 설정
*/