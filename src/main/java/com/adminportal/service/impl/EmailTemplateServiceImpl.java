package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.EmailTemplate;
import com.adminportal.repository.EmailTemplateRepository;
import com.adminportal.service.EmailTemplateService;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService{
	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Override
	public void save(EmailTemplate emailTemplate) {
		emailTemplateRepository.save(emailTemplate);
	}

	@Override
	public List<EmailTemplate> findAll() {
		return (List<EmailTemplate>) emailTemplateRepository.findAll();
	}

	@Override
	public EmailTemplate findByTitle(String title) {
		return emailTemplateRepository.findByTitle(title);
	}
}
