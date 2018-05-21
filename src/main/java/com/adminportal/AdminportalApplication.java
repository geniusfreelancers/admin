package com.adminportal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner {
	
	
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(AdminportalApplication.class, args);
	    }
	 
	 /*@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		    User user1 = new User();
			user1.setUsername("jainson");
			user1.setUserType("Admin");
			user1.setPassword(SecurityUtility.passwordEncoder().encode("98044Admin"));
			user1.setEmail("kolystylefashion@gmail.com");
			Set<UserRole> userRoles = new HashSet<>();
			Role role1 = new Role();
			role1.setRoleId(0);
			role1.setName("ROLE_ADMIN");
			userRoles.add(new UserRole(user1, role1));
			
			userService.createUser(user1, userRoles);
	        return builder.sources(AdminportalApplication.class);
	    }*/
	 @Override
		public void run(String... args) throws Exception{
			
			
		}

/*
import com.braintreegateway.BraintreeGateway;

@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner{

//	public static String DEFAULT_CONFIG_FILENAME = "config.properties";
//    public static BraintreeGateway gateway;
    
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {

		SpringApplication.run(AdminportalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		User user1 = new User();
		user1.setUsername("admin");
		user1.setUserType("Admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("admin@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}
	*/

}
