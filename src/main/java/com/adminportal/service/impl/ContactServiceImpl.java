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

	@SuppressWarnings("unchecked")
	public List<Contact> searchContacts(String keyword) {
		List<Contact> contactList = contactRepository.findByEmail(keyword);
		if(contactList == null || contactList.size() == 0) {
			contactList = contactRepository.findByOrderNumber(keyword);
		}
		if(contactList == null || contactList.size() == 0) {
			contactList = contactRepository.findByFullName(keyword);
		}
		if(contactList == null || contactList.size() == 0) {
			contactList = (List<Contact>) contactRepository.findOne(Long.valueOf(keyword).longValue());
		}
		return contactList;
	}

	@Override
	public List<Contact> findTop10ByOrderByIdDesc() {
		return contactRepository.findTop10ByOrderByIdDesc();
	}

	public Contact findById(Long id) {
		return contactRepository.findOne(id);
	}
	
	
}
