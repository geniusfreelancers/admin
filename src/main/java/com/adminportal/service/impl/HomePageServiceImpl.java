package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.adminportal.domain.HomePage;
import com.adminportal.repository.HomePageRepository;
import com.adminportal.service.HomePageService;

public class HomePageServiceImpl implements HomePageService{

	@Autowired
	private HomePageRepository homePageRepository;
	
	public HomePage updateHomePage(HomePage homePage) {
		return homePageRepository.save(homePage);	
	}
	
	public HomePage findOne(Long id) {
		return homePageRepository.findOne(id);	
	}
	
	public List<HomePage> findAll() {
		return (List<HomePage>) homePageRepository.findAll();	
	}

	public void save(HomePage homePage) {
		homePageRepository.save(homePage);
		
	}
}
