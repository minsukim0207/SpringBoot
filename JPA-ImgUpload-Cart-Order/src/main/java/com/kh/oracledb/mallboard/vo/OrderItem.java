package com.kh.oracledb.mallboard.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orderItem_seq")
	@SequenceGenerator(name="orderItem_seq", sequenceName="orderItem_seq", allocationSize=1)
	private int id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="order_id")
	private Order order;
	
	// 구매자
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	// 주문 번호
	private int itemId;
	// 상품 이름
	private String itemName;
	// 상품 가격
	private int itemPrice;
	// 상품 수량
	private int itemCount;
	// 총 가격
	private int itemTotalPrice;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="saleItem_id")
	private SaleItem saleItem;
	
	// 주문 취소 여부 (1:주문취소 0:주문완료)
	private int isCancle;
	
	// 장바구니 상품 하나씩 개별 주문
	public static OrderItem createOrderItem(int itemId, User user, Item item, int count, Order order, SaleItem saleItem) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setUser(user);
		orderItem.setOrder(order);
		orderItem.setItemName(item.getName());
		orderItem.setItemPrice(item.getPrice());
		orderItem.setItemCount(count);
		orderItem.setItemTotalPrice(item.getPrice()*count);
		orderItem.setSaleItem(saleItem);
		return orderItem;
	}
	
	// 장바구니 전체 주문
	public static OrderItem createOrderItem(int itemId, User user, CartItem cartItem, SaleItem saleItem) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setUser(user);
		orderItem.setItemName(cartItem.getItem().getName());
		orderItem.setItemPrice(cartItem.getItem().getPrice());
		orderItem.setItemCount(cartItem.getCartCount());
		orderItem.setItemTotalPrice(cartItem.getItem().getPrice()*cartItem.getCartCount());
		orderItem.setSaleItem(saleItem);
		return orderItem;
	}
}
