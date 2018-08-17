package com.mashup.hotel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mashup.hotel.model.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest,Integer>{

	
	 @Query("SELECT a FROM Guest a WHERE a.firstName=:firstName and a.contact=:contact")
	 Guest findByfirstNameAndcontact(@Param("firstName") String firstName, @Param("contact") String contact);

//	 Guest findBy

	//Guest findByfirstName(String getfirstName);
}
