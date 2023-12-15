package com.kh.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
// UserDetailsService : 사용자 정보 인증
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.spring.model.SiteUser;
import com.kh.spring.model.User;
import com.kh.spring.model.UserRole;
import com.kh.spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

	private final UserRepository userRepository;
	
	// 유저에 대한 정보를 로그인할 때 userDetails를 사용해서 로그인할 수 있는 유저가 있는지 확인
	// 사용자명을 기준으로 사용자 정보를 가져오게 할 것
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SiteUser> _siteUser = userRepository.findByUsername(username);
		if (_siteUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없음");
		}
		SiteUser user = _siteUser.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 만약 admin user로 로그인된다면 로그인 분류를 role에 따라 추가로 작성
		if ("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
}

// UserDetails : 스프링 시큐리티가 사용자의 인증과 권한 부여를 처리하는데 필요한 정보 제공
// 인터페이스로 다양한 종류의 메서드가 존재
/*
UserDetails 메서드
getAuthorities() : 사용자가 가지고 있는 권한 목록 반환
					권한은 정의된 권한에 따라 달라지고 개발자가 설정
					권한은 GrantedAuthority를 사용해서 가져옴
getPassword() : 사용자의 비밀번호 반환
				데이버베이스에서 암호화 처리된 형태로 저장되어 있음
getUsername() : 사용자명을 반환
isEnables() : 계정이 활성화되어 있는지 여부
*/