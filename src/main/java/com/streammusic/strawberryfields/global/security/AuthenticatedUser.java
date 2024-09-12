package com.streammusic.strawberryfields.global.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;

public class AuthenticatedUser implements UserDetails, RequestUser {

	private final transient User user;

	public AuthenticatedUser(User user) {
		this.user = user;
	}

	@Override
	public Long getUserId() {
		return this.user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of((GrantedAuthority)() -> "ROLE_USER");
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
