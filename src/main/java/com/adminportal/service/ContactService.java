package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Contact;
import com.adminportal.domain.InquiryRespond;

public interface ContactService {
	List<Contact> findAllByOrderByIdDesc();

	List<Contact> searchContacts(String keyword);

	List<Contact> findTop10ByOrderByIdDesc();

	Contact findById(Long id);

	String saveResponse(InquiryRespond inquiryRespond);

	Contact save(Contact contact);
}
