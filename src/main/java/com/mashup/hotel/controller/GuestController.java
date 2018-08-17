package com.mashup.hotel.controller;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashup.hotel.dao.GuestRepository;
import com.mashup.hotel.model.Guest;
import com.mashup.hotel.model.StatusDetails;
import com.mashup.hotel.service.GuestService;
import com.mashup.hotel.service.GuestServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/guest")
@Api(description = "endpoints for guest  checkin.")
public class GuestController {

	@Autowired
	GuestService guestservice;
	
	@Autowired
	GuestRepository guestRepository;
	
	
	@RequestMapping(value="/checkIn", method=RequestMethod.POST)
	  @ApiOperation("Stores the checkin detail of guest.")
	public ResponseEntity<?> checkIn(@Valid  @RequestBody Guest guest) {
		guest.setDate(System.currentTimeMillis());
		
		if(guestRepository.findByfirstNameAndcontact(guest.getfirstName(),guest.getContact())!=null) {
       	 StatusDetails errorDetails = new StatusDetails(new Date(), "Already CheckedIn",
		     "Guest with name "+guest.getfirstName()+" with contact "+guest.getContact()+"already checked In, Contact admin"
		     		+ "to re checkin");
   		 return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }
		
		guestRepository.save(guest);
		 return new ResponseEntity(new StatusDetails(new Date(),
				 "Checked In","Guest "+guest.getfirstName()+"checkIn"), HttpStatus.ACCEPTED);
		
	}
	
}
