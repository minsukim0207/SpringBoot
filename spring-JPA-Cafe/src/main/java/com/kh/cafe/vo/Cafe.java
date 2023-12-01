package com.kh.cafe.vo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Cafe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cafe_sequence")
	@SequenceGenerator(name="cafe_sequence", sequenceName="cafe_sequence", allocationSize=1)
	private Long cafeId;
	private String name;
	private String location;
	private String openingHours;
}
