package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Contact;
import com.adminportal.repository.ContactRepository;
import com.adminportal.service.ContactService;
@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> findAllByOrderByIdDesc(){
		return contactRepository.findAllByOrderByIdDesc();
	}
	
}
