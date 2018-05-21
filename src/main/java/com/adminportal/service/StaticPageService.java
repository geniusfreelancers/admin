package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.StaticPage;

public interface StaticPageService {

	StaticPage getPageByTitle(String title);
	StaticPage findByPagename(String pagename);
	List<StaticPage> findAll();
	StaticPage findById(Long id);
	
}
