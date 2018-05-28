package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Newsletter;
import com.adminportal.repository.NewsletterRepository;
import com.adminportal.service.NewsletterService;
@Service
public class NewsletterServiceImpl implements NewsletterService{
	@Autowired
	private NewsletterRepository newsletterRepository;
	
	public List<Newsletter> findAllByOrderByIdDesc(){
		return newsletterRepository.findAllByOrderByIdDesc();
	}
	
	public Long subscribersCount() {
		return newsletterRepository.count();
	}

	public List<Newsletter> searchSubscribers(String keyword) {
		List<Newsletter> subscriberList = newsletterRepository.findByEmail(keyword);
		return subscriberList;
	}
	
}
