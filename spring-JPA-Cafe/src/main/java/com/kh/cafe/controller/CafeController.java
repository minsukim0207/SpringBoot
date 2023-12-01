package com.kh.cafe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafe.service.CafeService;
import com.kh.cafe.vo.Cafe;

@Controller
@RequestMapping("/cafes")
public class CafeController {
	
	private final CafeService cafeService;
	
	@Autowired
	public CafeController(CafeService cafeService) {
		this.cafeService = cafeService;
	}
	
	@GetMapping
	public String getAllCafes(Model model, @RequestParam(required=false) String name) {	// @RequestParam(required=false) : 파라미터를 필수로 적어주지 않아도 됨을 나타냄
		// 카페 리스트를 만들어준 후
		// 만약 리스트에서 카페가 존재한다면 그 카페 목록만 보여주고
		// 존재하지 않는다면 모든 카페 목록을 보여주겠다
		// @PathVariable과 @RequestParam의 차이
		// @PathVariable : URL 경로에서 변수 값을 추출
		// @RequestParam : 한 경로 안에서 클라이언트가 요청한 파라미터 값을 추출 // url/cafe?name=사용자가입력한값
		List<Cafe> cafes;
		if (name != null && !name.isEmpty()) {
			cafes = cafeService.findCafes(name);
		} else {
			cafes = cafeService.getAllCafes();
		}
		model.addAttribute("cafes", cafes);
		return "cafe-list";
	}
	
	@GetMapping("/new")
	public String showCafeForm(Model model) {
		model.addAttribute("cafe", new Cafe());
		return "cafe-form";
	}
	
	@PostMapping("/new")
	public String saveCafe(@ModelAttribute Cafe cafe) {
		cafeService.saveCafe(cafe);
		return "redirect:/cafes";
	}
	
	@GetMapping("/{id}")
	public String detailCafe(@PathVariable Long id, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(id);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafe-detail";
	}
	
	@GetMapping("/update/{id}")
	public String updateCafe(@PathVariable Long id, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(id);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafe-form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCafe(@PathVariable Long id) {
		cafeService.deleteCafeById(id);
		return "redirect:/cafes";
	}
	
	@GetMapping("/search")
	public String searchCafes(@RequestParam String keyword, Model model) {
		List<Cafe> cafes = cafeService.findCafes(keyword);
		model.addAttribute("cafes", cafes);
		return "search-result";
	}
	
	@GetMapping("/count/{location}")
	public String countCafesByLocation(@PathVariable String location, Model model) {
		int cafeCount = cafeService.countCafesByLocation(location);
		// 1. 지역값을 저장할 model
		model.addAttribute("location", location);
		// 2. 지역 개수를 저장할 model
		model.addAttribute("cafeCount", cafeCount);
		return "cafe-count";
	}
	
	// 카페 존재 확인
	@GetMapping("/exists/{name}")
	public String existsCafeByName(@PathVariable String name, Model model) {
		boolean cafeExists = cafeService.existsCafeByName(name);
		model.addAttribute("cafeExists", cafeExists);
		return "cafe-exists";
	}
}
