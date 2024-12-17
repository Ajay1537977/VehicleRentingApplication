package com.example.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    PasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
	 return	http
			 // csrf -> cross site request forgery it is token mechanism where it will protect crsf attrack
			 //httprequest -> which 
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(authorize->authorize
				.requestMatchers("/customer-register","/RentingPartner-register","/save-vehicle","/find-all-vehicle","/update-vehicle")
				.permitAll()
				.anyRequest()
				.authenticated())
		.formLogin(Customizer.withDefaults())//http session based authentication
		.build();
		
	}

}
