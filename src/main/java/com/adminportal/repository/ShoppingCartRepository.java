package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{
	ShoppingCart findBySessionId(String sessionid);

	ShoppingCart findByBagId(String bagId);

	List<ShoppingCart> findTop10ByOrderByUpdatedDateDesc();

	List<ShoppingCart> findAllByOrderByUpdatedDateDesc();
}
