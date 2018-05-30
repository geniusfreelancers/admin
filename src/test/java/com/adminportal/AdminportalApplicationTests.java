package com.adminportal;
/*
import java.util.Locale;*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*import com.adminportal.domain.Order;
import com.adminportal.utility.MailConstructor;*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminportalApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	/*@Test
	public void sendEmail() {
		Order order = new Order();
		
		MailConstructor mailConstructor = new MailConstructor();
		mailConstructor.constructGuestOrderConfirmationEmail(order, Locale.ENGLISH);
	}*/

}
