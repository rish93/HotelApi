package com.mashup.hotel.controller;

import java.util.Date;

import javax.validation.Valid;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashup.JwtTokenProvider;
import com.mashup.SecurityConfig;
import com.mashup.hotel.dao.ApplicationUserRepository;
import com.mashup.hotel.model.ApplicationUser;
import com.mashup.hotel.model.ErrorDetails;
import com.mashup.hotel.model.JwtAuthenticationResponse;
import com.mashup.hotel.service.UserDetailServiceImpl;



@RestController
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;
    private ApplicationUserRepository applicationUserRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;
	   private UserDetailServiceImpl userDetailsService;

	    public LoginController(ApplicationUserRepository applicationUserRepository,
	                          BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.applicationUserRepository = applicationUserRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }


	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser( @RequestBody ApplicationUser loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = tokenProvider.generateToken(authentication);
	        System.out.println("--"+jwt+"--");
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }	    
	   
	    @PostMapping("/signup")
	    public ResponseEntity<?> signUp(@RequestBody ApplicationUser user) {
	    	 if(applicationUserRepository.findByUsername(user.getUsername())!=null) {
	          
	    		 ErrorDetails errorDetails = new ErrorDetails(new Date(), "Username already taken",
	    			     "Enter unique Username"  );
	    		 return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	         }
	    	
	    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    	 applicationUserRepository.save(user);
	        if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
	            System.out.println("User :" + user.getUsername() + " added to repository");
	        }
	        return new  ResponseEntity("Registered",HttpStatus.ACCEPTED);
	    }
	
}
