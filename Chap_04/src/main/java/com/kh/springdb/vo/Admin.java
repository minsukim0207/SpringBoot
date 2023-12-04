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
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="admin_seq")
	@SequenceGenerator(name="admin_seq", sequenceName="admin_seq", allocationSize=1)
	private Long id;
	private String adminId;
	private String adminPw;

}
