package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.User;

public class UserDetailsImpl implements UserDetails{
	private final User user;

	public UserDetailsImpl(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
		
		
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}

}
