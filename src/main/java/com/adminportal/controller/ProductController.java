package com.adminportal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Category;
import com.adminportal.domain.Product;
import com.adminportal.domain.SubCategory;
import com.adminportal.domain.SubSubCategory;
import com.adminportal.domain.User;
import com.adminportal.service.CategoryService;
import com.adminportal.service.ProductService;
import com.adminportal.service.UserService;

import com.adminportal.service.impl.AmazonClient;

@Controller
@RequestMapping("/product")
public class ProductController {
	

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
    ProductController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
	

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProduct(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		Product product = new Product();
		List<Category> categories =  categoryService.findAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "addProduct";
	}
	

	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductPost(
			@ModelAttribute("product") Product product, HttpServletRequest request,@AuthenticationPrincipal User activeUser,
			Model model){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		Date addedDate = Calendar.getInstance().getTime();
		product.setAddedDate(addedDate);	
	//	productService.save(product);
		 //Get the uploaded files and store them
		MultipartFile coverImage = product.getProductCoverImage();
		String coverImageName = null;
		if(coverImage == null || coverImage.isEmpty()) {
			model.addAttribute("missingCoverImage",true);
			return "addProduct";
		}else {
			model.addAttribute("missingCoverImage",false);
			coverImageName = amazonClient.uploadFile(coverImage);
			product.setCoverImageName(coverImageName);
		}
		String productImageName = coverImageName;
        List<MultipartFile> files = product.getProductImage();
        if (files != null && files.size() > 0) 
        {
            for (MultipartFile multipartFile : files) {
            	
            	String newFileName = amazonClient.uploadFile(multipartFile);
            	productImageName = productImageName+","+newFileName;
			}
         }
        product.setProductImagesName(productImageName);
        productService.save(product);
		return "redirect:productList";
	}
	

	@RequestMapping("/productInfo")
	public String productInfo(@RequestParam("id") Long id, Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		Product product = productService.findOne(id);
		String availableSize = product.getSize();
		List<String> sizeList = Arrays.asList(availableSize.split("\\s*,\\s*"));

		String productImages = product.getProductImagesName();
		List<String> productImageList = Arrays.asList(productImages.split("\\s*,\\s*"));
	
		
		if(product.getSoldProducts() != null){
			model.addAttribute("soldProduct", true);
		}else {
			model.addAttribute("soldProduct", false);
		}
		model.addAttribute("sizeList", sizeList);
		model.addAttribute("productImageList", productImageList);
		model.addAttribute("product", product);

		String fileUrl = endpointUrl + "/" + bucketName + "/";
		model.addAttribute("fileUrl", fileUrl);

		return "productInfo";
		
	}
	
	
	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		Product product = productService.findOne(id);
		List<Category> categories =  categoryService.findAllCategories();
		for (Category cat : categories) {
			if(cat.getId()==product.getCategory().getId()) {
				List<SubCategory> subcategories =  categoryService.findAllSubCategoriesByCategory(cat);
				model.addAttribute("subcategories", subcategories);
				
				for(SubCategory subCat : subcategories) {
					if(subCat.getId()== product.getSubCategory().getId()) {
						List<SubSubCategory> subsubcategories =  categoryService.findAllSubSubCategoriesBySubCategory(subCat);
						model.addAttribute("subsubcategories", subsubcategories);
					}
				}
			}
		}
		
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "updateProduct";
	}
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(
			@Valid @ModelAttribute("product") Product product, BindingResult result, @ModelAttribute("removefile") boolean removefile, HttpServletRequest request) {
		String productImageName = null;
		//@ModelAttribute("removeCover") boolean removeCover,
		boolean removeCover = false;
		//////////////////
		MultipartFile coverImage = product.getProductCoverImage();
		String coverImageName = product.getCoverImageName();
		String coverImageRemove = product.getCoverImageName();
		if(coverImage == null || coverImage.isEmpty()) {
			product.setCoverImageName(coverImageName);
		}else {
			coverImageName = amazonClient.uploadFile(coverImage);
			product.setCoverImageName(coverImageName);
			removeCover = true;
		}
		productImageName = coverImageName;
		String getProductImagesName = product.getProductImagesName();
		List<String> imageList = Arrays.asList(getProductImagesName.split("\\s*,\\s*"));
		Collection<String> c = new ArrayList<String>(imageList);
		
		if(removefile) {
			//int count = imageList.size();
			for(String image : imageList) {
				
					if(!removeCover && coverImageRemove.equalsIgnoreCase(image)) {
						c.remove(image);
						System.out.println("Removing All Images except Cover Image "+image);
					
					}else {
						amazonClient.deleteFileFromS3BucketByFilename(image);
						c.remove(image);
						System.out.println("Removing All Images "+image);
					}
				
			}
			
    	}
		if(coverImageName.equalsIgnoreCase(coverImageRemove)) {
			
		}else {
			getProductImagesName = coverImageName+","+c.toString();
		}
		
		getProductImagesName = getProductImagesName.replace("[", "");
		getProductImagesName = getProductImagesName.replace("]", "");
		productService.save(product);
        List<MultipartFile> files = product.getProductImage();
        if (files != null && files.size() > 0 && !files.isEmpty()) 
        {
	            for (MultipartFile multipartFile : files) {
	            	
	            	String newFileName = amazonClient.uploadFile(multipartFile);
	            	productImageName = getProductImagesName+","+newFileName;
				}
        	
        }
        productImageName = productImageName.replace(",,", ",");
		product.setProductImagesName(productImageName);
		productService.save(product);
		return "redirect:/product/productInfo?id="+product.getId();
	}
	
	
/*	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(
			@ModelAttribute("product") Product product, HttpServletRequest request
			){
		productService.save(product);
		
		MultipartFile productImage = product.getProductImage();
		
		if(!productImage.isEmpty()){
			try{
				byte[] bytes = productImage.getBytes();
			
				//Name to assign to image
				String name = product.getId()+".png";
				Files.delete(Paths.get("src/main/resources/static/image/product/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+name)));
			    stream.write(bytes);
			    stream.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		return "redirect:/product/productInfo?id="+product.getId();
	}*/
	
	
	@RequestMapping("/productList")
	public String productList(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<Product> productList = productService.findAllByOrderByIdDesc();

		String fileUrl = endpointUrl + "/" + bucketName + "/";
		// Only get published products need to implement that logic here
		model.addAttribute("productList", productList);
		model.addAttribute("fileUrl", fileUrl);

		return "productList";
		
	}
	
	@RequestMapping("/soldInventory")
	public String soldInventory(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<Product> productList = productService.findSoldInventory();
		String fileUrl = endpointUrl + "/" + bucketName + "/";
		// Only get published products need to implement that logic here
		model.addAttribute("productList", productList);
		model.addAttribute("fileUrl", fileUrl);

		return "soldInventory";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			){
		productService.removeOne(Long.parseLong(id.substring(11)));
		List<Product> productList = productService.findAll();
		
		model.addAttribute("productList", productList);
		
		return "redirect:/product/productList";
		
	}
	
	
}
