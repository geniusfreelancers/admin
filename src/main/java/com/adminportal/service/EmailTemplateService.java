package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.EmailTemplate;

public interface EmailTemplateService {

	void save(EmailTemplate emailTemplate);

	List<EmailTemplate> findAll();

	EmailTemplate findByTitle(String title);

}
