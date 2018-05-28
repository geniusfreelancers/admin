package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
	User findByEmail(String email);
	List<User> findByUserType(String userType);

	List<User> findByPhone(String keyword);

	List<User> findByFirstName(String keyword);

	List<User> findByLastName(String keyword);

	List<User> findByMailingZipcode(String keyword);
}
