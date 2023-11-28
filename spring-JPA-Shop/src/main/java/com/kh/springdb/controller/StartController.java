package com.kh.springdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springdb.vo.Product;

@RestController
public class StartController {

	@GetMapping(value = "/test")
	public Product startProduct() {
		Product pd = new Product();
		pd.setProduct_id(null);
		pd.setProduct_name("kh");
		return pd;
	}
}

/*
 * @Controller와 @RestController
 * 
 * @Controller
 * 어노테이션을 사용하는 전통적인 SpringMVC 패턴을 적용한 것
 * View를 생성하고 반환하는 역할을 하기도 함
 * 주로 @RequestMapping과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄
 * 데이터를 반환할때는 Model 객체를 통해 View에 전달
 * 
 * @RestController
 * 조금 더 RESTful 웹 서비스를 제공하는데 특화된 어노테이션
 * @Controller에 @ResponseBody를 함께 사용한 것과 유사하게 동작
 * 이런 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션
 * 주로 @Controller View(html파일)을 반환하는데 사용되고
 * @RestController는 @Controller에 @ResponseBody를 추가로 사용하는 것을 대체할 수 있어 코드가 간결해짐
 * 
 * @ResponseBody
 * 메서드가 return해서 반환해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌
 * 자바 코드에서 직접 본문(body)으로 전달해서 사용할 수 있는 어노테이션
 */
