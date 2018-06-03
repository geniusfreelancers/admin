package com.adminportal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.HomePage;
import com.adminportal.domain.HomePageAdditional;

public interface HomePageService {
	HomePage updateHomePage(HomePage homePage);
	HomePage findOne(Long id);
	List<HomePage> findAll();
	void save(HomePage homePage);
	String updateImage(String imageName, MultipartFile image);
	HomePageAdditional findAdditionalHomePage(Long id);
	void saveAdditionalHomePage(HomePageAdditional homePageAdditional);
}
