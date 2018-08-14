package com.mashup.hotel.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mashup.hotel.model.Admin;

@Repository
public interface AdminRepository  extends CrudRepository<Admin,String>{

	Admin findByUsernameOrEmail(String username,String email);

	Optional<Admin> findById(Long id);
}
