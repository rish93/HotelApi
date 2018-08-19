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

	
//	 @Query("SELECT a FROM Guest a WHERE a.firstName=:firstName and a.contact=:contact")
//	 Guest findByfirstNameAndcontact(@Param("firstName") String firstName, @Param("contact") String contact);

	 List<Guest> findAllByAge(Integer age);
	 
	 @Query(value="SELECT a FROM Guest a WHERE a.firstName=:firstName and a.contact=:contact")
	 List<Guest> findAllByfirstNameAndcontact(@Param("firstName") String firstName,
			 										@Param("contact") String contact);


	@Modifying(clearAutomatically = true)
    @Transactional
	@Query("update Guest guest set guest.checkOutTime =:checkOutTime where guest.id =:id")
	void updateCheckOutTime(@Param("checkOutTime") String checkOutTime, @Param("id") Integer id);

	 List<Guest> findAllByCheckInTime(String time);
	 
	 Guest findByContact(String contact);

	 @Query(value="SELECT a FROM Guest a WHERE  :checkOutTime is null and a.contact=:contact")
	 List<Guest> findAllBycheckOutTimeAndContact(@Param("checkOutTime") String checkOutTime,
		 										@Param("contact") String contact);
	 @Query(value="SELECT usr FROM Guest usr")
	 List<Guest> findAllGuest();
	 
	 
}
