package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Category;
import com.adminportal.domain.SubCategory;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long>{

	List<SubCategory> findByCategory(Category category);

}
