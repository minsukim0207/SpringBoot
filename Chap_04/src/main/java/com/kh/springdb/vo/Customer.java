package com.kh.springdb.vo;

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
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	@SequenceGenerator(name="customer_seq", sequenceName="customer_seq", allocationSize=1)
	private Long id;
	private String nickname;
	private String password;
	private String email;
	

}
