package com.mashup.hotel.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name="guest")
public class Guest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Transient
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@Column
	private String checkInTime;
	
	@Column
	@NotNull
	@Size(min=2, message="Name should have atleast 2 characters")
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	@NotNull
	@Size(min=10,max=10, message="contact should have atleast 10 digits")

	private String contact;
	
	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	

	@Column
	@NotNull
	@Email(message="Email not well formed")
	private String email; 
	
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return checkInTime;
	}

	public void setDate(long dateMilisecond) {
		Date date= new Date(dateMilisecond);
		this.checkInTime=sdf.format(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	public void setDate(String date) {
//		this.checkInTime = date;
//	}
	
	
}
