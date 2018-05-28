package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findAllByOrderByIdDesc();

	List<Contact> findByEmail(String keyword);

	List<Contact> findByOrderNumber(String keyword);

	List<Contact> findByFullName(String keyword);

	List<Contact> findTop10ByOrderByIdDesc();

}

