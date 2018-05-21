package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.SubCategory;
import com.adminportal.domain.SubSubCategory;

public interface SubSubCategoryRepository extends CrudRepository<SubSubCategory, Long>{

	List<SubSubCategory> findBySubCategory(SubCategory subCategory);

}
