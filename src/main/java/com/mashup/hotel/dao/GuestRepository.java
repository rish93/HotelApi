package com.mashup.hotel.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mashup.hotel.model.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest,String>{

}
