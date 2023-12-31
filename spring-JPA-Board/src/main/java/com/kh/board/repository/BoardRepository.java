package com.kh.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.board.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// 게시판의 제목에 특정 키워드가 포함된 게시물 검색
	@Query("SELECT b FROM BOARD b WHERE b.TITLE LIKE %:keyword%")
	//@Query("SELECT * FROM BOARD WHERE TITLE LIKE %:keyword%")
	/* Board b 와의 차이는 엔티티에서 별칭을 지정해서 사용하는 방식의 차이
	 * JPQL - Java Persistence Query Language
	 * Java 객체를 대상으로 하는 쿼리
	 * JPA에서 사용
	 * 엔티티 객체와 필드에 대한 쿼리를 정의하는데 사용
	 * JPQL 엔티티와 필드에 대한 쿼리를 작성할 떄 SQL과는 조금 다른 문법을 사용
	 */
	List<Board> findTitle(@Param("keyword") String keyword);
}

/*
@Query : JPA에서 제공하는 CRUD 쿼리 이외에 추가적으로 필요한 쿼리가 있을 경우 직접 생성해서 쿼리를 정의할 때 사용하는 어노테이션
		인터페이스 메서드에 직접 쿼리를 작성할 수 있으며 더 복잡한 작업 수행 가능

%:keyword% : keyword 파라미터로 받아온 키워드를 나타냄
			% 어떤 문자열이라도 매칭이 될 수 있도록 도와주는 역할을 함
List<Board> : 검색 결과를 리스트 형태로 반환할 수 있도록 해줌
@Param("keyword") : keyword에 해당하는 값을 메서드의 파라미터로 @Param이라는 어노테이션 사용
					메서드에서 매개변수로 전달된 keyword의 값을 쿼리 내의 :keyword에 매핑
*/