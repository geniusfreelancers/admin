package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Reviews;
import com.adminportal.repository.ReviewsRepository;
import com.adminportal.service.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService{
	@Autowired
	private ReviewsRepository reviewsRepository;

	@Override
	public List<Reviews> findByStatusOrderByIdDesc(String status) {
		return reviewsRepository.findByStatusOrderByIdDesc(status);
	}
}
