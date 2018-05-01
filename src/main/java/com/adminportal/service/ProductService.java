package com.adminportal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Product;

public interface ProductService {
	
	Product save(Product product);
	List<Product> findAllByOrderByIdDesc();
	List<Product> findAll();
	
	Product findOne(Long id);
	
	void removeOne(Long id);
	

}
