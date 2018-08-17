package com.mashup.hotel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mashup.hotel.model.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest,Integer>{

	
	 @Query("SELECT a FROM Guest a WHERE a.firstName=:firstName and a.contact=:contact")
	 Guest findByfirstNameAndcontact(@Param("firstName") String firstName, @Param("contact") String contact);

	 List<Guest> findAllByAge(Integer age);
	 
//	 @Modifying
//	 @Transactional
//	 @Query("DELETE FROM Guest a WHERE a.firstName=:? and a.contact=:?")
//	 void deleteGuestByNameAndContact(@Param("firstName") String firstName, @Param("contact") String contact);
	
	 List<Guest> findAllByCheckInTime(String time);
	 
	 Guest findByContact(String contact);
	 
}
