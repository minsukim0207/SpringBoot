package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="payments")
@Getter
@Setter
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="paymentId_seq")
	@SequenceGenerator(name="paymentId_seq", sequenceName="paymentId_seq", allocationSize=1)
	private Long id;	// 주문 번호
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	private String paymentStatus;
}
