package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Category;
import com.adminportal.domain.SubCategory;
import com.adminportal.domain.SubSubCategory;

public interface CategoryService {
	Category save(Category category);
	SubCategory save(SubCategory subcategory);
	SubSubCategory save(SubSubCategory subsubcategory);
	Category findOne(Long id);
	SubCategory findOneSub(Long id);
	SubSubCategory findOneSubSub(Long id);
	List<Category> findAllCategories();
	List<SubCategory> findAllSubCategories();
	List<SubSubCategory> findAllSubSubCategories();
	List<SubCategory> findAllSubCategoriesByCategory(Category category);
	List<SubSubCategory> findAllSubSubCategoriesBySubCategory(SubCategory subCategory);
}
