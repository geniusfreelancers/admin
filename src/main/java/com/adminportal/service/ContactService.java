package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Contact;

public interface ContactService {
	List<Contact> findAllByOrderByIdDesc();

	List<Contact> searchContacts(String keyword);

	List<Contact> findTop10ByOrderByIdDesc();

	Contact findById(Long id);
}
