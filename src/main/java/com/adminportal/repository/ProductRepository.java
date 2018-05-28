package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Category;
import com.adminportal.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findAllByOrderByIdDesc();

	List<Product> findByTitleContaining(String keyword);

	List<Product> findBySku(String keyword);

	List<Product> findByDescriptionContaining(String keyword);

	Long countByCategory(Category category);
}
