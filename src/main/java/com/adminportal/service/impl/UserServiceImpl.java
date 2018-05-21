package com.adminportal.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Order;
import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;


	
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null){
			LOG.info("user {} already exists. Nothing will be done", user.getUsername());
		}else{
			for(UserRole ur : userRoles){
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
		}
		return localUser;
	}
	
	@Override
	public User save(User user){
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	@Override
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findById(Long id){
		return userRepository.findOne(id);
	}
	
	@Override
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
}
