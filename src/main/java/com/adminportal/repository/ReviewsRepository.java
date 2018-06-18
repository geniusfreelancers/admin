package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Reviews;

public interface ReviewsRepository  extends CrudRepository<Reviews, Long>{
	
	List<Reviews> findByStatusOrderByIdDesc(String status);

}
