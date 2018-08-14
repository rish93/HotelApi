package com.mashup.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashup.hotel.dao.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	AdminRepository adminRepository;




}
