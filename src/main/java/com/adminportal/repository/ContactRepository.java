package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findAllByOrderByIdDesc();

}

