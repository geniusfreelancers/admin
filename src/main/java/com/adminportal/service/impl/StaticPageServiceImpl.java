package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.StaticPage;
import com.adminportal.repository.StaticPageRepository;
import com.adminportal.service.StaticPageService;

@Service
public class StaticPageServiceImpl implements StaticPageService{

	@Autowired
	private StaticPageRepository staticPageRepository;
	
	public StaticPage getPageByTitle(String title) {
		return staticPageRepository.findByTitle(title);
	}
	
	public StaticPage findByPagename(String pagename) {
		return staticPageRepository.findByPagename(pagename);
	}

	
	public List<StaticPage> findAll() {
		return (List<StaticPage>) staticPageRepository.findAll();
	}

	
	public StaticPage findById(Long id) {
		return staticPageRepository.findOne(id);
	}

	@SuppressWarnings("unchecked")
	public List<StaticPage> searchPages(String keyword) {
		List<StaticPage> staticPageList = (List<StaticPage>) staticPageRepository.findByTitle(keyword);
		
		if(staticPageList == null || staticPageList.size() == 0) {
			staticPageList = (List<StaticPage>) staticPageRepository.findByPagename(keyword);
		}
		if(staticPageList == null || staticPageList.size() == 0) {
			staticPageList = staticPageRepository.findByTitleContaining(keyword);
		}
		return staticPageList;
	}
}
