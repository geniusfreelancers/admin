package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Product;
import com.adminportal.repository.ProductRepository;
import com.adminportal.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product){
		return productRepository.save(product);
	}

	public List<Product> findAll(){
		return (List<Product>) productRepository.findAll();
	}
	
	public List<Product> findAllByOrderByIdDesc(){
		return productRepository.findAllByOrderByIdDesc();
	}
	public Product findOne(Long id){
		return productRepository.findOne(id);
	}
	
	public void removeOne(Long id){
		productRepository.delete(id);
	}
	
	public Long productCount() {
		return productRepository.count();
		
	}
}
