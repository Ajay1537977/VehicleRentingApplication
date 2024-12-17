package com.example.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.User;
import com.example.enums.UserRole;
import com.example.exception.FailedToUploadException;
import com.example.exception.ImageIsNotFoundById;
import com.example.exception.UserNotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.ImageRepository;
import com.example.repository.UserRepository;
import com.example.requestDto.UserRequest;
import com.example.responseDto.UserResponse;


@Service
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public UserResponse findUser(int userId) {
		Optional<User> optional=userRepository.findById(userId);
		if(optional.isPresent())
		{
		User user=optional.get();
		UserResponse response= userMapper.mapToUserResponse(user);
		 this.setProfilePictureURL(response, user.getUserId());
		 return response;
		}
		else
		{
			throw new UserNotFoundException("user not found");
		}	
	}

	public void setProfilePictureURL(UserResponse response, int userId) {
    int imageId=userRepository.getProfilePictureIdByUserId(userId);
    if(imageId >0)
    	response.setProfilePicture("/fetch-image?imageId=" + imageId);	
	}

	public UserResponse register(UserRequest userRequest, UserRole customer) {
		userRequest.setRole(UserRole.CUSTOMER);
		 User user=userMapper.mapToCustomer(userRequest,customer);
		   user.setPassword(passwordEncoder.encode(user.getPassword()));
	      User savedUser=userRepository.save(user);
	      return userMapper.mapToUserResponse(savedUser);
		
	}


	public  UserResponse registers(UserRequest userRequest, UserRole rentingPartner) {
		userRequest.setRole(UserRole.RENTING_PARTNER);
		User user=userMapper.mapToRentingPartner(userRequest,rentingPartner);
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
	      User savedUser=userRepository.save(user);
	      return userMapper.mapToUserResponse(savedUser);
	}
public UserResponse updateUser(UserRequest request,int userId) {
		
		Optional<User> optional = userRepository.findById(userId);

		if (optional.isPresent()) {
			User user = userMapper.mapToUser(request, optional.get());
			
			userRepository.save(user);
			
			UserResponse response = userMapper.mapToUserResponse(user);
			this.setProfilePictureURL(response, userId); 
			
			return response;
			
		} else {
			throw new UserNotFoundException("Failed To Find The User");
		}
	}



	


	

	
	


}
