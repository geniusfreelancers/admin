package com.adminportal.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.adminportal.domain.Order;
import com.adminportal.domain.OrderLog;

@Transactional
public interface OrderLogRepository extends CrudRepository<OrderLog, Long> {
	List<OrderLog> findByOrder(Order order);
	List<OrderLog> findAllByOrderByIdDesc();
	List<OrderLog> findByOrderOrderByIdDesc(Order order);
	
}
