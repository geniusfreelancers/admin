package com.adminportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Product;
import com.adminportal.domain.SoldProducts;
import com.adminportal.repository.CategoryRepository;
import com.adminportal.repository.ProductRepository;
import com.adminportal.repository.SoldProductsRepository;
import com.adminportal.repository.SubCategoryRepository;
import com.adminportal.repository.SubSubCategoryRepository;
import com.adminportal.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SoldProductsRepository soldProductsRepository;
	@Autowired 
	public CategoryRepository categoryRepository;
	@Autowired 
	public SubCategoryRepository subCategoryRepository;
	@Autowired 
	public SubSubCategoryRepository subSubCategoryRepository;
	
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
	
	public Long soldProductsCount() {
		int soldQty = 0;
		List<SoldProducts> soldProductsList = (List<SoldProducts>) soldProductsRepository.findAll();
		if(soldProductsList != null) {
			for (SoldProducts soldProducts : soldProductsList) {
				soldQty = soldQty + soldProducts.getSoldQty();
			}
		}
		return Long.valueOf(soldQty);	
	}
	
	public List<Product> findSoldInventory(){
		List<SoldProducts> soldProductsList = (List<SoldProducts>) soldProductsRepository.findAllByOrderByIdDesc();
		List<Product> productList = new ArrayList<Product>();
		if(soldProductsList != null) {
		for(SoldProducts soldProducts : soldProductsList) {
			productList.add(soldProducts.getProduct());
		}
		}else {
			return null;
		}
		return productList;
	}

	
	
	public List<Product> searchProducts(String keyword) {
		List<Product> productList = productRepository.findBySku(keyword);
		if (productList.size() == 0){
			productList = productRepository.findByTitleContaining(keyword);
		}
		if (productList.size() == 0){
			productList = productRepository.findByDescriptionContaining(keyword);
		}
		return productList;
	}
	
}
