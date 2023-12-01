package com.kh.cafe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.cafe.repository.CafeRepository;
import com.kh.cafe.vo.Cafe;

@Service
public class CafeService {
	
	@Autowired
	private final CafeRepository cafeRepository;
	
	public CafeService(CafeRepository cafeRepository) {
		this.cafeRepository = cafeRepository;
	}
	
	public List<Cafe> getAllCafes() {
		return cafeRepository.findAll();
	}
	
	public Optional<Cafe> getCafeById(Long id) {
		return cafeRepository.findById(id);
	}
	
	public Cafe saveCafe(Cafe cafe) {
		return cafeRepository.save(cafe);
	}
	
	public void deleteCafeById(Long id) {
		cafeRepository.deleteById(id);
	}

	public List<Cafe> findCafes(String keyword) {
		//return cafeRepository.findCafe(keyword);
		return cafeRepository.findByNameContaining(keyword);
	}
	
	// repository에 작성한 지역카운트를 이용하는 메서드
	public int countCafesByLocation(String location) {
		return cafeRepository.countByLocation(location);
	}
	
	// 카페 존재 여부
	public boolean existsCafeByName(String name) {
		return cafeRepository.existsByName(name);
	}
}
