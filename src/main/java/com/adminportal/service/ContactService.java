package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Contact;

public interface ContactService {
	List<Contact> findAllByOrderByIdDesc();
}
