package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.EmailTemplate;

public interface EmailTemplateRepository extends CrudRepository<EmailTemplate, Long>{

	EmailTemplate findByTitle(String title);


}
