package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Category;
import com.adminportal.domain.SubCategory;
import com.adminportal.domain.SubSubCategory;
import com.adminportal.repository.CategoryRepository;
import com.adminportal.repository.SubCategoryRepository;
import com.adminportal.repository.SubSubCategoryRepository;
import com.adminportal.service.CategoryService;

@Service
public class CategoryServiceImpl implements  CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private SubSubCategoryRepository subSubCategoryRepository;
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public SubCategory save(SubCategory subcategory) {
		return subCategoryRepository.save(subcategory);
	}
	
	public SubSubCategory save(SubSubCategory subsubcategory) {
		return subSubCategoryRepository.save(subsubcategory);
	}
	
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
		
	}
	public SubCategory findOneSub(Long id) {
		return subCategoryRepository.findOne(id);
		
	}
	public SubSubCategory findOneSubSub(Long id) {
		return subSubCategoryRepository.findOne(id);
		
	}
	public List<Category> findAllCategories(){
		return (List<Category>) categoryRepository.findAll();
	}
	
	public List<SubCategory> findAllSubCategories(){
		return (List<SubCategory>) subCategoryRepository.findAll();
	}
	
	public List<SubSubCategory> findAllSubSubCategories(){
		return (List<SubSubCategory>) subSubCategoryRepository.findAll();
	}
	
	public List<SubCategory> findAllSubCategoriesByCategory(Category category){
		return (List<SubCategory>) subCategoryRepository.findByCategory(category);
	}
	
	public List<SubSubCategory> findAllSubSubCategoriesBySubCategory(SubCategory subCategory){
		return (List<SubSubCategory>) subSubCategoryRepository.findBySubCategory(subCategory);
	}
	
	
}
