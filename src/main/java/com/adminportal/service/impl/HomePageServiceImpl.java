package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import com.adminportal.domain.HomePage;
import com.adminportal.repository.HomePageRepository;
import com.adminportal.service.HomePageService;
@Service
public class HomePageServiceImpl implements HomePageService{
		private AmazonClient amazonClient;
		@Value("${amazonProperties.endpointUrl}")
	    private String endpointUrl;
	    @Value("${amazonProperties.bucketName}")
	    private String bucketName;
	    @Value("${amazonProperties.accessKey}")
	    private String accessKey;
	    @Value("${amazonProperties.secretKey}")
	    private String secretKey;
	    @Autowired
	    HomePageServiceImpl(AmazonClient amazonClient) {
	        this.amazonClient = amazonClient;
	    }
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
	
	public String updateImage(String imageName, MultipartFile image) {
		if(image != null && !image.isEmpty()) {
			amazonClient.deleteFileFromS3BucketByFilename(imageName);
			imageName = amazonClient.uploadFile(image);	
		}
		return imageName;
	}
}
