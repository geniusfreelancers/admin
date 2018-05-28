package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Newsletter;

public interface NewsletterService {
	List<Newsletter> findAllByOrderByIdDesc();
	Long subscribersCount();
	List<Newsletter> searchSubscribers(String keyword);
}
