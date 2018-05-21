package com.adminportal.service;

import java.util.List;
import java.util.Set;

import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;
import com.adminportal.domain.UserBilling;
import com.adminportal.domain.UserPayment;
import com.adminportal.domain.UserShipping;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);
	User save(User user);
	User findByUsername(String username);
	List<User> findAll();
	User findByEmail(String email);
	
	User findById(Long id);
	/*void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void updateUserShipping(UserShipping userShipping, User user);
	
	void setUserDefaultPayment(Long userPaymentId, User user);
	
	void setUserDefaultShipping(Long userShippingId, User user);*/
}
