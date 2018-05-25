package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.SoldProducts;

public interface SoldProductsRepository extends CrudRepository<SoldProducts, Long>{
	
	List<SoldProducts> findAllByOrderByIdDesc();

}
