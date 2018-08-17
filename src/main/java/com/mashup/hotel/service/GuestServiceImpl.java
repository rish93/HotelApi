package com.mashup.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashup.hotel.dao.GuestRepository;
import com.mashup.hotel.model.Guest;

@Service
public class GuestServiceImpl  implements GuestService{

//	@Autowired
//	Guest guest;
	
	@Autowired
	GuestRepository guestRepository;
	
//	public void guestCheckIn(Guest guest,long date)
//	{
//
//		//Guest guest = new Guest();
//		
//	}

	@Override
	public void guestCheckIn(Guest guest,Long currentTime) {
		// TODO Auto-generated method stub

		guest.setfirstName(guest.getfirstName());
		guest.setLastName(guest.getLastName());
		guest.setDate(currentTime);
		guest.setEmail(guest.getEmail());
		guest.setContact(guest.getContact());
		guestRepository.save(guest);

		guestRepository.findAll().forEach(s -> System.out.println("date: "+s.getCheckInTime()+""
				+ "\n firstname: "+s.getfirstName()+"\n"
				+ " lastname: "+s.getLastName()+"\n email:"+
		s.getEmail()+"\n id:"+s.getId()+"\n contact:"+s.getContact()));
	}
	
}
