package com.example.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;

import com.example.repository.UserRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository repository;
	

	public UserDetailServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	     Optional<User> optional= repository.findByEmail(username);
	    		 if(optional.isEmpty())
	    		 {
	    			 throw new UsernameNotFoundException("user not found");
	    		 }
		return new UserDetailsImpl(optional.get());
	}

}
