package com.kh.cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.cafe.vo.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
	
	// 카페 존재 여부 확인 메서드
	boolean existsByName(String name);
	
	// count를 이용해서 지역의 개수 확인하는 메서드
	int countByLocation(String location);
	
	/* 특정 문자열 포함한 엔티티 검색 메서드
	@Query("SELECT C FROM CAFE C WHERE C.NAME LIKE %keyword%")
	List<Cafe> findCafe(@Param("keword") String keyword);
	*/
	
	// 특정 문자열을 포함한 엔티티를 검색하는데 사용하는 메서드
	List<Cafe> findByNameContaining(String keyword);
}

/*
Query Creation : Spring Data JPA에서 제공하는 기능
				메서드에 규칙이 존재하고 규칙에 따라서 메서드를 생성해주는 기능
				메서드 이름으로 데이터베이스 쿼리를 생성

	ex)
	private Long cafeId;
	private String name;
	private String location;
	private String openingHours;
	
	지역을 검색하고 싶다면
	findByLocation(String location)
	=> SELECT * FROM CAFE WHERE LOCATION = ?
	
	운영시간을 검색하고 싶다면
	findByOpeningHours(String openingHours)
	=> SELECT * FROM CAFE WHERE OPENINGHOURS = ?
	
	총 갯수를 계산해주는 메서드를 만들고 싶다면
	countBy클래스의변수명
	countByLocation(String location)
	=> SELECT COUNT(*) FROM CAFE WHERE LOCATION = ?
	
	존재 여부 확인해주는 메서드를 만들고 싶다면
	existsBy클래스의변수명
	existsByLocation(String location)
	=> SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM CAFE WHERE LOCATION = ?
	
	삭제하고 싶다면
	deleteBy클래스의변수명
	deleteByLocation(String location)
	=> DELETE FROM CAFE WHERE LOCATION = ?
	
Query -> AND OR IS EQUALS BETWEEN AFTER BEFORE LIKE ORDERBY IN FALSE TRUE IGNORE	// 대문자? 소문자?
	
	1. JPA에서 And 사용
		findBy변수명And다른변수명
	2. JPA에서 Or 사용
		findBy변수명Or다른변수명
	3. JPA에서 Is 또는 Equals 사용
		findBy변수명Is
		findBy변수명Equals
	4. JPA에서 BETWEEN AFTER BEFORE LIKE 사용
		findBy변수명Between
		findBy변수명After
		findBy변수명Before
		findBy변수명Like
	5. JPA에서 ORDERBY 사용
		findBy변수명OrderBy정렬하고자하는기준변수명Desc
		findBy변수명OrderBy정렬하고자하는기준변수명Asc
	6. JPA에서 IN 구문 사용
		findBy변수명In(List<예약어> 변수명)
	7. JPA에서 TRUE FALSE 구문 사용
		findBy변수명True()
		findBy변수명False()
	8. JPA에서 IgnoreCase 사용
		findBy변수명IgnoreCase
*/