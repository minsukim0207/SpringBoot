package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="memberId_seq")
	@SequenceGenerator(name="memberId_seq", sequenceName="memberId_seq", allocationSize=1)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String address;
}
