package com.mashup.hotel.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/guest")
@Api(description = "endpoints for guest  checkin.")
public class GuestController {

	 private final Logger log = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	GuestRepository guestRepository;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkIn", method=RequestMethod.POST)
	@ApiOperation("Stores the checkin detail of guest.")
	public ResponseEntity<?> checkIn(@Valid  @RequestBody Guest guest) {
		guest.setDate(System.currentTimeMillis());
		List<Guest> guestRep=guestRepository.findAllByfirstNameAndcontact(guest.getFirstName(),
			     guest.getContact());
		if(guestRep!=null )
		{
			for(Guest guestLoggedIn: guestRep) {
				if(guestLoggedIn.getCheckOutTime()==null) {
						 StatusDetails errorDetails = new StatusDetails(new Date(), "Already CheckedIn",
					     "Guest with same name and contact already checked In, Contact admin"
					     + "to enable re checkin");
						 log.error("Guest with same name and contact already checked In, "
						 		+ "contact admin for explicit checkout");
			   		 return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
				   }
			}
		}
		List<Guest>guestListRep =guestRepository.findAllGuest();
		System.out.println(guestListRep);
		if(guestListRep!=null) {
			for(Guest guestRep_All : guestListRep) {
				System.out.println("---------------------------------------------------------------------"); 
					if(guestRep_All.getContact().equals(guest.getContact()) && guestRep_All.getCheckOutTime()==null){
					log.error("Same contact can't check in twice, contact admin for explicit checkout");
						return new ResponseEntity(new StatusDetails(new Date(),
							 "Contact No already registered for checkIn not checked out"," use another number or contact admin for checkOut"), HttpStatus.BAD_REQUEST);
			        }
		      }
	     }
		 log.info("checkin for the guest "+guest.getFirstName()+" completed");
		 guestRepository.save(guest);
		 return new ResponseEntity(new StatusDetails(new Date(),
		                            "Checked In","Guest name "+guest.getFirstName()+" checkedIn"),
				                     HttpStatus.ACCEPTED);
	 }
	
}
