package com.kh.spring.shop.service;

import com.kh.spring.shop.repository.OrderRepository;
import com.kh.spring.shop.repository.PaymentRepository;
import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Cart;
import com.kh.spring.shop.vo.Order;
import com.kh.spring.shop.vo.Payment;
import com.kh.spring.shop.vo.Product;

public class ShopService {

	public Cart cart = new Cart();
	
	private ProductRepository productRepository;
	private PaymentRepository paymentRepository;
	private OrderRepository orderRepository;
	public Order placeOrder(Product product, int quantity) {
		// 상품 추가
		cart.addToCart(product, quantity);
		
		// 주문 번호 생성
		Order order = createOrder(product, quantity);
		
		return order;
	}

	// 주문 정보 생성
	public Order createOrder(Product product, int quantity) {
		Order order = new Order();
		order.setProduct(product);
		order.setQuantity(quantity);
		return order;
	}
	
	// 주문 정보 조회
	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}
	
	// 상품 정보 조회
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}
	
	// 결제 처리
	private Payment savePayment(Order order, String payment) {
		Payment payments = new Payment();
		payments.setOrder(order);
		payments.setPaymentStatus(payment);
		return payments;
	}

}
