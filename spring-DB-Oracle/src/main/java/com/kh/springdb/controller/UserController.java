package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.springdb.model.User;
import com.kh.springdb.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// 전체 아이디 조회를 위해 GetMapping 사용
	@GetMapping("/users-information")
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users-information";
	}
	
	
	// 한개 아이디 가져오기
	@GetMapping("/user-information/{id}")
	public String getUserById(@PathVariable int id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user-information";
	}

	@GetMapping("/register")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@GetMapping("/registersuccess")
	public String getRegisterSuccess() {
		return "success";
	}
	
	// 회원 추가
	@PostMapping("/api/user/register")
	public String registerUser(@ModelAttribute("user") @Validated User user, BindingResult result) {
		userService.registerUser(user);
		return "redirect:/registersuccess";	// 회원가입을 성공한 경우 이동하는 경로
	}
	
	@GetMapping("/user-update/{id}")
	public String updateUserForm(@PathVariable int id, Model model) {
	// 사용자 정보를 가져와서 모엘에 추가
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user-update";
	}
	
	// id에 해당하는 Update문을 사용해서 수정
	@PostMapping("/api/user/update/{id}")
	public String updateUser(@PathVariable int id, @ModelAttribute("user") @Validated User user, BindingResult result) {
		if (result.hasErrors()) {
			return "에러가 발견되었습니다.";
		}
		user.setMno(id); // 경로에서 받은 id를 이용해 사용자 정보 업데이트
		userService.updateUser(user);
		return "redirect:/user-infomation";
	}
	
	
	@RequestMapping(value = "/user-delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "redirect:/user-information";
	}
	
	/*
	@DeleteMapping("/user-delete/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "redirect:/user-information";
	}
	*/
}


/*
@PathVariable : 경로에 대한 변수를 메서드의 매개변수로 받을 때 사용
사용법 : @PathVariable int id

@ModelAttribute("값")
타임리프 뷰에서 설정한 값의 이름을 사용해서 모델 속성에 접근 가능
@ModelAttribute("user") : user라는 이름으로 Model에 User 객체를 추가

@Validated : 데이터 유효성 검사를 실시하도록 행하는 것
@Validated(user) : User 객체에 대한 데이터 유효성 검사를 실시하겠다 한 것

BindingResult : @Validated 실시한 유효성 검사 결과를 저장하는 객체
				유효성 검사에서 발생한 오류에대한 정보가 담기는 공간
				
@RequestMapping : 사용자의 http 요청을 특정 메서드와 할 수 있도록 매핑하는(감싸주는) 역할
	value = "" : 사용자가 요청할 때 주고받는 url을 작성
				예를 들어 value="/user-delete/{id}" 값이 있을 때 {id}는 PathVariable로 실제 요청할 경우 해당 url 위치로 들어오는 값을 변수로 활용할 수 있음
	method = "" : 메서드에서 처리할 http 요청을 메서드에 지정
				RequestMethod.GET : HTTP GET 메서드는 주로 DB에 정보를 요청하기 위해 사용
									사이트에서 URL을 통한 직접 요청이나 링크를 클릭해야하는 상황에서 GET메서드가 사용됨
									데이터를 서버로 전송하지는 않고, 주로 데이터를 요청하거나 조회할 때에만 사용 가능
				RequestMethod.POST : 주로 서버로 데이터를 제출하기 위해 사용
									데이터를 HTML 본문에 담아 전송하기 때문에 GET보다는 대량의 데이터를 처리하기에 적합
*/