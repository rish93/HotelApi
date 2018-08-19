package com.mashup.hotel.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashup.JwtTokenProvider;
import com.mashup.hotel.dao.ApplicationUserRepository;
import com.mashup.hotel.model.ApplicationUser;
import com.mashup.hotel.model.StatusDetails;
import com.mashup.hotel.model.JwtAuthenticationResponse;
import com.mashup.hotel.service.UserDetailServiceImpl;



@RestController
@RequestMapping(value="/login")
public class LoginController {

	 private final Logger log = LoggerFactory.getLogger(LoginController.class);
	
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
	        log.info("token generated "+ jwt);
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }	    
	   
	    @PostMapping("/signup")
	    public ResponseEntity<?> signUp(@RequestBody ApplicationUser user) {

	    	if(applicationUserRepository.findByUsername(user.getUsername())!=null) {
	        	 StatusDetails errorDetails = new StatusDetails(new Date(), "Username already taken",
			     "Enter unique Username"  );
	        	 log.info("Username already exist");
	        	 return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	         }
	   	    StatusDetails statusDetails = null;
	    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    	applicationUserRepository.save(user);
	        if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
	        	  statusDetails = new StatusDetails(new Date(), "Username "+user.getUsername()+" registered",
	    			     "User with Admin privileges, needs token based authentication ");
	        }
	         log.info("user registered");
	        return new  ResponseEntity(statusDetails,HttpStatus.ACCEPTED);
	    }
	
}
