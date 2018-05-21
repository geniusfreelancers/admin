package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
