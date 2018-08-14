package com.mashup.hotel.service;

import com.mashup.hotel.model.Guest;

public interface GuestService {

	
	public void guestCheckIn(Guest guest, Long currentTime);
}
