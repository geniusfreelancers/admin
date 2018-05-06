package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Newsletter;

public interface NewsletterRepository extends CrudRepository<Newsletter, Long>{
	List<Newsletter> findAllByOrderByIdDesc();

}

