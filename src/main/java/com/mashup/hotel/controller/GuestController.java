package com.mashup.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashup.hotel.model.Guest;
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
	
	
	
	@RequestMapping(value="/checkIn", method=RequestMethod.POST)
	  @ApiOperation("Stores the checkin detail of guest.")
	public void checkIn(@Valid  @RequestBody Guest guest) {
		   guestservice.guestCheckIn(guest, System.currentTimeMillis());
	}
	
}
