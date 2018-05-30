package com.adminportal.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adminportal.domain.EmailTemplate;
import com.adminportal.domain.User;
import com.adminportal.service.StorageService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.StorageFileNotFoundException;


@Controller
public class TemplateUploadController {

    private final StorageService storageService;

    @Autowired
    public TemplateUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/templates", method=RequestMethod.GET)
    public String listUploadedFiles(Model model,@AuthenticationPrincipal User activeUser) throws IOException {
    	User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
    	EmailTemplate emailTemplate = new EmailTemplate();
    	 model.addAttribute("emailTemplate",emailTemplate);
       /* model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(TemplateUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));*/

        return "uploadForm";
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
    	MultipartFile file = emailTemplate.getTemplateFile();
        storageService.emailTemplateUpload(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/templates";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}