package com.kh.spring.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name="Products")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_seq")
	@SequenceGenerator(name="product_seq", sequenceName="product_seq", allocationSize=1)
	private int id;
	
	private String name;
	private String text;
	private String price;
	private int count;
	private int stock;
	private int isSoldout;
	@OneToMany(mappedBy="Products", cascade=CascadeType.ALL)
	private List<Comment> comments;
	private String imgName;
	private String imgPath;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
	public void createDate() {
		this.createDate = LocalDate.now();
	}
}
