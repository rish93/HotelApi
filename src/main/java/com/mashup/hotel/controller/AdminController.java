package com.mashup.hotel.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashup.hotel.dao.GuestRepository;
import com.mashup.hotel.model.Guest;
import com.mashup.hotel.model.StatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin")
@Api(description = "endpoint for admin related functionality.")
public class AdminController {

	
 @Autowired
 GuestRepository guestRepository;

 @RequestMapping(value = "/allGuest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
 @ApiOperation("retrieves all the guest checked in")
 public @ResponseBody List < Guest > getAllGuest() {
  List < Guest > allCheckedInGuest = new ArrayList < Guest > ();
  Iterator < Guest > itr = guestRepository.findAll().iterator();
  while (itr.hasNext()) {
	  allCheckedInGuest.add(itr.next());
  }
  return allCheckedInGuest;
 }

 
 @RequestMapping(value = "/guest/remove", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
 @ApiOperation("retrieves the checkin guest based on first name and contact.")
   public  ResponseEntity deleteGuest (@RequestBody Guest guest)
   {
	 StatusDetails errorDetails,responseDetails=null;
	 if(guest!=null) {
    	 guestRepository.delete(guest);
	
    	if( guestRepository.findByfirstNameAndcontact(guest.getFirstName(), 
    			guest.getContact())==null) {
    		responseDetails= new StatusDetails(new Date(System.currentTimeMillis()),
    				"checked in guest deleted","guest with name \"+guest.getFirstName()+\" deleted");
    		 return new ResponseEntity(responseDetails, HttpStatus.ACCEPTED);
    	}
	 
	 }
	 errorDetails= new StatusDetails(new Date(System.currentTimeMillis()),"Not able to remove ", "est not removed, try later");
	 return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
   }
 
 
  @RequestMapping(value = "/guest/bycheckInTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("retrieves the checkin guest based on checkIn time.")
  public @ResponseBody List < Guest > getAllGuestByCheckInTime(@RequestParam("checkInTime") String checkInTime) {
    return guestRepository.findAllByCheckInTime(checkInTime);
 }

  
  @RequestMapping(value = "/guest/byAge", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("retrieves the checkin guest based on checkIn time.")
  public @ResponseBody List < Guest > getAllGuestByAge(@RequestParam("age") Integer age) {
    return guestRepository.findAllByAge(age);
 }

}