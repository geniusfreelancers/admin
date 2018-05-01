package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Order;
import com.adminportal.domain.OrderLog;

public interface OrderLogService {

	List<OrderLog> findAll();
	List<OrderLog> findAllByOrderByIdDesc();
	List<OrderLog> findByOrderByOrderByIdDesc(Order order);

}
