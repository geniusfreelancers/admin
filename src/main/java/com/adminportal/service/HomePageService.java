package com.adminportal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.HomePage;

public interface HomePageService {
	HomePage updateHomePage(HomePage homePage);
	HomePage findOne(Long id);
	List<HomePage> findAll();
	void save(HomePage homePage);
	String updateImage(String imageName, MultipartFile image);
}
