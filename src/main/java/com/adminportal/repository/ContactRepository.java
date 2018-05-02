package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	

}

