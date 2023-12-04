package com.kh.springdb.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="STUDENT_MEMBER")
public class StudentMember {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="st_seq")
	@SequenceGenerator(name="st_seq", sequenceName="st_seq", allocationSize=1)
	private Long studentNumber;
	private String mdmberName;
	private int koranScore;
	private int englishScore;
	private int mathScore;
}
