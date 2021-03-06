package com.adminportal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adminportal.domain.Category;
import com.adminportal.domain.Product;
import com.adminportal.domain.SubCategory;
import com.adminportal.domain.SubSubCategory;
import com.adminportal.domain.User;
import com.adminportal.service.CategoryService;
import com.adminportal.service.UserService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/categories")
	public String categoryList(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		
		return "categories";
	}
	
	@RequestMapping("/addcategory")
	public String addcategory(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		List<Category> supercategoryList = new ArrayList<Category>();
		for (Category supercategory : categoryList) {
			if(supercategory.getSubCategory().size() > 0) {
				supercategoryList.add(supercategory);
			}
		}
		model.addAttribute("supercategoryList", supercategoryList);
		List<SubCategory> subcategoryList = categoryService.findAllSubCategories();
		model.addAttribute("subcategoryList", subcategoryList);
		List<SubSubCategory> subsubcategoryList = categoryService.findAllSubSubCategories();
		model.addAttribute("subsubcategoryList", subsubcategoryList);
		Category category = new Category();
		model.addAttribute("category", category);
		SubCategory subcategory = new SubCategory();
		model.addAttribute("subcategory", subcategory);
		SubSubCategory subsubcategory = new SubSubCategory();
		model.addAttribute("subsubcategory", subsubcategory);
		return "addcategory";
	}
	
	@RequestMapping(value="/addmaincategory", method=RequestMethod.POST)
	public @ResponseBody
	List<Category> addmaincategory(@ModelAttribute("category") Category newcategory,Model model){
		categoryService.save(newcategory);
		List<Category> newcategoryList = categoryService.findAllCategories();
		return newcategoryList;
	}
	
	//Update Category
	@RequestMapping("/updatecategory")
	public String updateCategory(@RequestParam("id") Long id, Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		Category category = categoryService.findOne(id);
		model.addAttribute("category", category);
		return "editcategory";
	}
	
	
	@RequestMapping(value="/updatecategory", method=RequestMethod.POST)
	public String updateCategoryPost(@ModelAttribute("category") Category category, BindingResult result, Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		categoryService.save(category);
		model.addAttribute("updateSuccess",true);
		model.addAttribute("category", category);
		return "editcategory";
	}
	//Update Sub Category
	@RequestMapping("/updatesubcategory")
	public String updateSubCategory(@RequestParam("id") Long id, Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		SubCategory subcategory = categoryService.findOneSub(id);
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("subcategory", subcategory);
		model.addAttribute("categoryList", categoryList);
		return "editsubcategory";
	}
	
	
	@RequestMapping(value="/updatesubcategory", method=RequestMethod.POST)
	public String updateSubCategoryPost(@ModelAttribute("subcategory") SubCategory subcategory, BindingResult result, Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		categoryService.save(subcategory);
		model.addAttribute("updateSuccess",true);
		model.addAttribute("subcategory", subcategory);
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		return "editsubcategory";
	}
	
	//Update Sub Sub Category
		@RequestMapping("/updatesubsubcategory")
		public String updateSubSubCategory(@RequestParam("id") Long id, Model model,@AuthenticationPrincipal User activeUser){
			User user = userService.findByUsername(activeUser.getUsername());
	        model.addAttribute("user", user);
			SubSubCategory subsubcategory = categoryService.findOneSubSub(id);
			Category category = subsubcategory.getSubCategory().getCategory();
			List<SubCategory> subcategoryList = category.getSubCategory();
			List<Category> categoryList = categoryService.findAllCategories();
			model.addAttribute("subcategoryList", subcategoryList);
			model.addAttribute("subsubcategory", subsubcategory);
			model.addAttribute("categoryList", categoryList);
			return "editsubsubcategory";
		}
		
		
		@RequestMapping(value="/updatesubsubcategory", method=RequestMethod.POST)
		public String updateSubSubCategoryPost(@ModelAttribute("subsubcategory") SubSubCategory subsubcategory, BindingResult result, Model model,@AuthenticationPrincipal User activeUser){
			User user = userService.findByUsername(activeUser.getUsername());
	        model.addAttribute("user", user);
			categoryService.save(subsubcategory);
			model.addAttribute("updateSuccess",true);
			model.addAttribute("subsubcategory", subsubcategory);
			List<Category> categoryList = categoryService.findAllCategories();
			model.addAttribute("categoryList", categoryList);
			return "editsubsubcategory";
		}
	@RequestMapping(value="/getsubcategories", method=RequestMethod.GET)
	public @ResponseBody
	List<SubCategory> getsubcategories(@RequestParam(value= "id", required = true) Long id,Model model){
		Category category = categoryService.findOne(id);
		List<SubCategory> subcategories =  categoryService.findAllSubCategoriesByCategory(category);
		return subcategories;
	}
	
	@RequestMapping(value="/addsubcategory", method=RequestMethod.POST)
	public @ResponseBody
	SubCategory addsubcategory(@ModelAttribute("subcategory") SubCategory subcategory,@ModelAttribute("parentcategory") Long parentcategory,Model model){
		Category newcategory = categoryService.findOne(parentcategory);
		subcategory.setCategory(newcategory);
		categoryService.save(subcategory);
		
		return subcategory;
	}
		
	@RequestMapping(value="/getsubsubcategories", method=RequestMethod.GET)
	public @ResponseBody
	List<SubSubCategory> getsubsubcategories(@RequestParam(value= "id", required = true) Long id,Model model){
		SubCategory subCategory = categoryService.findOneSub(id);
		List<SubSubCategory> subsubcategories =  categoryService.findAllSubSubCategoriesBySubCategory(subCategory);
		return subsubcategories;
	}
	
	@RequestMapping(value="/addsubsubcategory", method=RequestMethod.POST)
	public @ResponseBody
	SubSubCategory addsubsubcategory(@ModelAttribute("subsubcategory") SubSubCategory subsubcategory,
			@ModelAttribute("subCategory") Long subcategory,
			Model model){
		
		SubCategory newsubcategory = categoryService.findOneSub(subcategory);
				
		subsubcategory.setSubCategory(newsubcategory);
		categoryService.save(subsubcategory);
		
		return subsubcategory;
	}
	
}
