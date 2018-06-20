package com.adminportal.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.domain.Contact;
import com.adminportal.domain.InquiryRespond;
import com.adminportal.domain.Order;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.service.ContactService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.AmazonClient;
import com.adminportal.utility.AmazonSESEmail;

@Controller
public class ContactController {
	private AmazonClient amazonClient;
	

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    @Autowired
    ContactController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
	@Autowired
	private UserService userService;
	@Autowired
	private	ContactService contactService;
	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private AmazonSESEmail amazonsesemail;
	@RequestMapping("/contactlists")
	public String contactLists(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
	//	List<Contact> contactList = (List<Contact>) contactRepository.findAll();
		List<Contact> contactList = (List<Contact>)contactService.findAllByOrderByIdDesc();
		if(contactList == null) {
			model.addAttribute("emptyContact", true);
			return "contactList";
		}else {
			model.addAttribute("emptyContact", false);
		}
		model.addAttribute("user",user);
		model.addAttribute("contactList",contactList);
		return "contactList";
	}
	
	@RequestMapping("/contactdetails/{id}")
	public String contactDetails(@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser) {
		Contact contact = contactService.findById(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        if(contact.getContactImage() == null) {
        	 model.addAttribute("attachment",false); 	
        }else {
        	 model.addAttribute("attachment",true);
        	 
        }
        model.addAttribute("contact",contact);
        return "contactdetails";
	}
	
	@RequestMapping("/contactdetails/attachments/{id}")
	public String contactAttachmentDetails(@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser) {
		Contact contact = contactService.findById(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        if(contact.getContactImage() == null) {
        	 model.addAttribute("attachment",false); 
        	 return "redirect:/contactdetails/"+id;
        }else {
        	    model.addAttribute("attachment",true);
        		String imageList = contact.getContactImage();
        		List<String> imageLists = Arrays.asList(imageList.split("\\s*,\\s*"));
        		String fileUrl = endpointUrl + "/" + bucketName + "/";
        		model.addAttribute("fileUrl", fileUrl);
        		model.addAttribute("imageLists",imageLists);
        }
        model.addAttribute("contact",contact);
        return "contactattachments";
	}
	
	
	@RequestMapping("/contactdetails/{status}/{id}")
	public String contactStatusUpdate(@PathVariable String status,@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser) {
		Contact contact = contactService.findById(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        contact.setStatus(status);
        contactService.save(contact);
        if(contact.getContactImage() == null) {
        	 model.addAttribute("attachment",false); 	
        }else {
        	 model.addAttribute("attachment",true); 
        }
        model.addAttribute("contact",contact);
        return "redirect:/contactdetails/"+id;
	}
	
	@RequestMapping(value="/contact/respond/{id}",  method=RequestMethod.POST)
	public String contactRespond(@PathVariable Long id,@ModelAttribute("responseText") String response, Model model,@AuthenticationPrincipal User activeUser) {
		Contact contact = contactService.findById(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        Date respondedDate = Calendar.getInstance().getTime();
        for(InquiryRespond response1 : contact.getInquiryRespondList()) {
        	if(!response1.isFromSystem()) {
        		response1.setOpened(true);
            	contactService.saveResponse(response1);
        	}
        }
        InquiryRespond respond = new InquiryRespond();
        respond.setResponseText(response);
        respond.setContact(contact);
        respond.setFromSystem(true);
        respond.setRespondedBy(user.getFirstName()+" "+user.getLastName());
        respond.setRespondDate(respondedDate);
        contactService.saveResponse(respond);
        contact.setRespondedDate(respondedDate);
        contactService.save(contact);
        //send email to user with the details
        amazonsesemail.constructInquiryResponseEmail(user,contact,respond, Locale.ENGLISH,"inquiryresponseemail");
        if(contact.getContactImage() == null) {
       	 model.addAttribute("attachment",false); 	
       }else {
       	 model.addAttribute("attachment",true);
       	 
       }
        model.addAttribute("contact",contact);
        return "redirect:/contactdetails/"+id;
	}
	

}
