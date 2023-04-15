package com.example.Training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.config.JwtUtils;
import com.example.Training.entity.JwtRequest;
import com.example.Training.entity.JwtResponse;
import com.example.Training.entity.User;
import com.example.Training.service.impl.UserDetailsServiceImpl;
import java.lang.Exception;
import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl usersDetailService;
	
	@Autowired
	private JwtUtils jwUtils;
	
	//generating token
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
				
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("user not fpund");
		}
		
		UserDetails userDetails = this.usersDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			
		}catch(DisabledException e) {
			throw new Exception("User Desabled"+e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid credentials"+e.getMessage());
		}
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)this.usersDetailService.loadUserByUsername(principal.getName()));
	}

}
