package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.User;
import com.example.enums.UserRole;
import com.example.mapper.UserMapper;
import com.example.requestDto.UserRequest;
import com.example.responseDto.UserResponse;
import com.example.service.UserService;
import com.example.util.ResponseStructure;
import com.example.util.SimpleResponseStructure;

@RestController
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("fetch-user")
	public ResponseEntity<ResponseStructure<UserResponse>> fetchUser(@RequestParam("user_id") int userId)
	{
	UserResponse userResponse=userService.findUser(userId);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseStructure.create(HttpStatus.OK.value(),"Image is Found", userResponse));
		
	}
	@PostMapping("/customer-register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerCustomer(@RequestBody UserRequest userRequest)
	{
     UserResponse userResponse=userService.register(userRequest,UserRole.CUSTOMER);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseStructure.create(HttpStatus.OK.value(),"successfully updated with customer", userResponse));
	}
	@PostMapping("/RentingPartner-register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerRentingPartner(@RequestBody UserRequest userRequest)
	{
	UserResponse userResponse=userService.registers(userRequest,UserRole.RENTING_PARTNER);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseStructure.create(HttpStatus.OK.value(),"Successfully updated with RentingPartner", userResponse));
	}
	@PutMapping("/update-user")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest request,@RequestParam int userId) {
		
		UserResponse response = userService.updateUser(request,userId);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "User Updated", response));
	}
	
	
	

}
