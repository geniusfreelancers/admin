package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.StaticPage;

public interface StaticPageRepository extends CrudRepository<StaticPage, Long>{

	StaticPage findByTitle(String title);
	
	StaticPage findByPagename(String pagename);

	List<StaticPage> findByTitleContaining(String keyword);

}
