package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.enums.UserRole;
import com.example.requestDto.UserRequest;
import com.example.responseDto.UserResponse;
@Component
public class UserMapper {
	/*private final UserRequest userRequest;
	private final UserResponse userResponse;
	public UserMapper(UserRequest userRequest, UserResponse userResponse) {
		super();
		this.userRequest = userRequest;
		this.userResponse = userResponse;
	}*/
	public User mapToCustomer(UserRequest request, UserRole role)
	{
		User user=new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setRole(role);
		return user;
	}
	
	public User mapToRentingPartner(UserRequest request, UserRole role)
	{
		User user=new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setRole(role);
		return user;
	}
	public UserResponse mapToUserResponse(User user)
	{
		UserResponse response=new UserResponse();
		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
        return response;
		
	}
	public User mapToUser(UserRequest request, User user) {

		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setPassword(request.getPassword());

		return user;
	}
	

}
