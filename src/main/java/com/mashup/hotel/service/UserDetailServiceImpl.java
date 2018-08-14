package com.mashup.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mashup.hotel.dao.ApplicationUserRepository;
import com.mashup.hotel.model.ApplicationUser;

import static java.util.Collections.emptyList;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	    public UserDetailServiceImpl(ApplicationUserRepository applicationUserRepository)
	    {
	        this.applicationUserRepository = applicationUserRepository;
	    }
	    
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
	        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
	        if (applicationUser == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new User(applicationUser.getUsername(), applicationUser.getPassword(),
	        		emptyList());
	        }
	
	public UserDetails loadUserById(Long userId)
			throws UsernameNotFoundException {
	        Optional<ApplicationUser> applicationUser = applicationUserRepository.findById(userId);
	        if (applicationUser == null && !applicationUser.isPresent()) {
	            throw new UsernameNotFoundException("not found ");
	        }else {
		        return new User(applicationUser.get().getUsername(), applicationUser.get().getPassword(),
		        		emptyList());
		        }
	        }
	
	

}
