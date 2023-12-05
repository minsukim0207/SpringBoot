package com.kh.oracledb.mallboard.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Entity
public class SaleItem {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="saleItem_seq")
	@SequenceGenerator(name="saleItem_seq", sequenceName="saleItem_seq", allocationSize=1)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sale_id")
	private Sale sale;
	
	// 판매자가 판매할 상품에 대한 정보 작성
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="seller_id")
	private User seller;
	
	// 판매자입장 : 고객이 주문한 상품 번호
	private int itemId;
	// 고객이 주문한 상품 이름
	private String itemName;
	// 고객이 주문한 상품 가격
	private int itemPrice;
	// 고객이 주문한 상품 수량
	private int itemCount;
	// 총 수익
	private int itemTotalPrice;
	
	@OneToOne(mappedBy="saleItem")
	private OrderItem orderItem;
	
	// 주문자가 주문 취소 여부(판매취소: 1 판매완료:0)
	private int isCancle;
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private LocalDate createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
	
	// 상품 개별 주문
	public static SaleItem createSaleItem(int itemId, Sale sale, User seller, Item item, int count) {
		SaleItem saleItem = new SaleItem();
		saleItem.setItemId(itemId);
		saleItem.setSale(sale);
		saleItem.setSeller(seller);
		saleItem.setItemName(item.getName());
		saleItem.setItemPrice(item.getPrice());
		saleItem.setItemCount(count);
		saleItem.setItemTotalPrice(item.getPrice()*count);
		return saleItem;
	}
	
	// 상품 전체 주문
	public static SaleItem createSaleItem(int itemId, Sale sale, User seller, CartItem cartItem) {
		SaleItem saleItem = new SaleItem();
		saleItem.setItemId(itemId);
		saleItem.setSale(sale);
		saleItem.setSeller(seller);
		saleItem.setItemName(cartItem.getItem().getName());
		saleItem.setItemPrice(cartItem.getItem().getPrice());
		saleItem.setItemTotalPrice(cartItem.getItem().getPrice()*cartItem.getCartCount());
		return saleItem;
	}
}
