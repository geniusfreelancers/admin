package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Reviews;

public interface ReviewsService {

	List<Reviews> findByStatusOrderByIdDesc(String status);

}
