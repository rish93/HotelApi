package com.mashup.hotel.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mashup.hotel.model.ApplicationUser;



@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);
	ApplicationUser findDistinctByUsername(String username);
}
