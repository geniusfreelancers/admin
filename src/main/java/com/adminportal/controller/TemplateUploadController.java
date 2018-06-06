package com.adminportal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adminportal.domain.EmailTemplate;
import com.adminportal.domain.Product;
import com.adminportal.domain.User;
import com.adminportal.service.EmailTemplateService;
import com.adminportal.service.ProductService;
import com.adminportal.service.StorageService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.StorageFileNotFoundException;
import com.adminportal.utility.MailConstructor;


@Controller
public class TemplateUploadController {

    private final StorageService storageService;

    @Autowired
    public TemplateUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
	private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    @Autowired
	private MailConstructor mailConstructor;
    @Autowired
    private EmailTemplateService emailTemplateService;
    @Autowired
    private ProductService productService;
    
    @RequestMapping(value="/templates", method=RequestMethod.GET)
    public String listUploadedFiles(Model model,@AuthenticationPrincipal User activeUser) throws IOException {
    	User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
    	EmailTemplate emailTemplate = new EmailTemplate();
    	model.addAttribute("emailTemplate",emailTemplate);
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(TemplateUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }
    
    
    @RequestMapping(value="/templates/alltemplates", method=RequestMethod.GET)
    public String listAllUploadedFiles(Model model,@AuthenticationPrincipal User activeUser) throws IOException {
    	User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
    	List<EmailTemplate> emailTemplateList = emailTemplateService.findAll();
    	if(emailTemplateList == null) {
    		model.addAttribute("emptyPage",true);
    	}else {
    		model.addAttribute("emptyPage",false);
    	}
    	model.addAttribute("emailTemplateList",emailTemplateList);

        return "alltemplates";
    }
    
    @RequestMapping(value="/files/{filename:.+}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    
    @RequestMapping(value="/templates", method=RequestMethod.POST)
    public String handleFileUpload(@ModelAttribute("emailTemplate") EmailTemplate emailTemplate,BindingResult result,
    		RedirectAttributes redirectAttributes,@AuthenticationPrincipal User activeUser,Model model) {
    	
    	User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        Date addedDate = Calendar.getInstance().getTime();
        emailTemplate.setAddedDate(addedDate);
    	MultipartFile file = emailTemplate.getTemplateFile();
        emailTemplate.setFileName(storageService.emailTemplateUpload(file));
        emailTemplate.setAddedBy(user.getFirstName()+" "+user.getLastName());
        emailTemplateService.save(emailTemplate);
       redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/templates";
    }

    @RequestMapping(value="/sendEmails", method=RequestMethod.GET)
    public String sendEmail(Model model,@AuthenticationPrincipal User activeUser) {
    	User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        List<EmailTemplate> emailTemplateList = emailTemplateService.findAll();
        List<String> emailTypeList = new ArrayList<String>();
        List<String> emailTitleList = new ArrayList<String>();
        for(EmailTemplate emailTemplate : emailTemplateList) {
        	emailTypeList.add(emailTemplate.getEmailType());
        	emailTitleList.add(emailTemplate.getTitle());
        }
        model.addAttribute("emailTypeList", emailTypeList);
        model.addAttribute("emailTitleList", emailTitleList);
        
    	return "sendemail";
    }
    
    @RequestMapping(value="/sendEmails", method=RequestMethod.POST)
    public String sendEmailPost(@ModelAttribute("to") String to,
    		@ModelAttribute("email") String email,
    		@ModelAttribute("emailType") String emailType,
    		@ModelAttribute("title") String title,
    		RedirectAttributes redirectAttributes,
    		Model model,@AuthenticationPrincipal User activeUser) {
    	User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        EmailTemplate emailTemplate = emailTemplateService.findByTitle(title);
        //Assigning Values for Email Preperation
        String subject = "Marketting Email";
        String senderEmail = "advertising@thetimesofeverest.com";
        String templateName = emailTemplate.getFileName();
        templateName = templateName.substring(0, templateName.indexOf("."));
        
        Product product = productService.findOne(new Long(1));
        //Send Email Now
        mailSender.send(mailConstructor.constructMarkettingEmail(templateName, email, subject, senderEmail, product, Locale.ENGLISH));
        redirectAttributes.addFlashAttribute("emailTemplate",emailTemplate);
        redirectAttributes.addFlashAttribute("message",
                "Email successfully sent to " + to + "!");
    	return "redirect:/sendEmails";
    }
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}