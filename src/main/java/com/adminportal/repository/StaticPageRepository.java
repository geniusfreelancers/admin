package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Category;
import com.adminportal.domain.StaticPage;

public interface StaticPageRepository extends CrudRepository<StaticPage, Long>{

	StaticPage findByTitle(String title);
	
	StaticPage findByPagename(String pagename);

}
