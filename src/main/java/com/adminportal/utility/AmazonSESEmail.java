package com.adminportal.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.adminportal.domain.Order;
import com.adminportal.domain.User; 
@Component
public class AmazonSESEmail {
	@Autowired
	private TemplateEngine templateEngine;
	// Replace sender@example.com with your "From" address.
	  // This address must be verified with Amazon SES.
	  static final String FROM = "andyjainson@gmail.com";

	  // Replace recipient@example.com with a "To" address. If your account
	  // is still in the sandbox, this address must be verified.
	  static final String TO = "kolystylefashion@gmail.com";
	  static final AmazonSimpleEmailService client = 
	          AmazonSimpleEmailServiceClientBuilder.standard()
	            .withRegion(Regions.US_EAST_1).build();
	  
		public String constructTestConfirmationEmail(User user, Order order, Locale locale,String template){
/*			AmazonSimpleEmailService client = 
			          AmazonSimpleEmailServiceClientBuilder.standard()
			            .withRegion(Regions.US_EAST_1).build();*/
			Context context = new Context();
			context.setVariable("order", order);
			context.setVariable("user", user);
			context.setVariable("cartItemList", order.getCartItemList());
			String text = templateEngine.process(template, context);
			String result = "Failed";
			try {
			SendEmailRequest request = new SendEmailRequest()
			          .withDestination(
			              new Destination().withToAddresses(TO))
			          .withMessage(new Message()
			              .withBody(new Body()
			                  .withHtml(new Content()
			                      .withCharset("UTF-8").withData(text))
			                  .withText(new Content()
			                      .withCharset("UTF-8").withData(text)))
			              .withSubject(new Content()
			                  .withCharset("UTF-8").withData("Order Confirmation - "+order.getId())))
			          .withSource(FROM);
			      client.sendEmail(request);
			      System.out.println("Email sent!");
			      result = "Passed";
			    } catch (Exception ex) {
			      System.out.println("The email was not sent. Error message: " 
			          + ex.getMessage());
			      result = ex.getMessage();
			    }
			return result;
		}
	  
/*	  public static void main(String[] args) throws IOException {

	    try {
	      AmazonSimpleEmailService client = 
	          AmazonSimpleEmailServiceClientBuilder.standard()
	          // Replace US_WEST_2 with the AWS Region you're using for
	          // Amazon SES.
	            .withRegion(Regions.US_WEST_2).build();
	      SendEmailRequest request = new SendEmailRequest()
	          .withDestination(
	              new Destination().withToAddresses(TO))
	          .withMessage(new Message()
	              .withBody(new Body()
	                  .withHtml(new Content()
	                      .withCharset("UTF-8").withData(HTMLBODY))
	                  .withText(new Content()
	                      .withCharset("UTF-8").withData(TEXTBODY)))
	              .withSubject(new Content()
	                  .withCharset("UTF-8").withData(SUBJECT)))
	          .withSource(FROM)
	          // Comment or remove the next line if you are not using a
	          // configuration set
	       //   .withConfigurationSetName(CONFIGSET);
	      client.sendEmail(request);
	      System.out.println("Email sent!");
	    } catch (Exception ex) {
	      System.out.println("The email was not sent. Error message: " 
	          + ex.getMessage());
	    }
	  }*/
	}