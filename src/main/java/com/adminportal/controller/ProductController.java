package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.amazonaws.services.s3.AmazonS3;


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
	
	@RequestMapping(value="/getsubcategories", method=RequestMethod.GET)
	public @ResponseBody
	List<SubCategory> getsubcategories(@RequestParam(value= "id", required = true) Long id,Model model){
		Category category = categoryService.findOne(id);
		List<SubCategory> subcategories =  categoryService.findAllSubCategoriesByCategory(category);
		return subcategories;
	}
	
	@RequestMapping(value="/getsubsubcategories", method=RequestMethod.GET)
	public @ResponseBody
	List<SubSubCategory> getsubsubcategories(@RequestParam(value= "id", required = true) Long id,Model model){
		SubCategory subCategory = categoryService.findOneSub(id);
		List<SubSubCategory> subsubcategories =  categoryService.findAllSubSubCategoriesBySubCategory(subCategory);
		return subsubcategories;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductPost(
			@ModelAttribute("product") Product product, HttpServletRequest request
			){
		Date addedDate = Calendar.getInstance().getTime();
		product.setAddedDate(addedDate);	
		productService.save(product);
		String productImageName = null;
		 //Get the uploaded files and store them
		int count =1;	
        List<MultipartFile> files = product.getProductImage();
        if (files != null && files.size() > 0) 
        {

        	
            for (MultipartFile multipartFile : files) {
            	
            	String newFileName = amazonClient.uploadFile(multipartFile);
				if(count == 1) {
					product.setCoverImageName(newFileName);
					productImageName = newFileName;
					count++;
					}else {

						productImageName = productImageName+","+newFileName;
					}

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
			@Valid @ModelAttribute("product") Product product, BindingResult result, @ModelAttribute("removefile") boolean removefile, @ModelAttribute("removeCover") boolean removeCover, HttpServletRequest request) {
		String productImageName = null;

		String fileUrl ="";

		if(removefile){
			String getProductImagesName = product.getProductImagesName();
			List<String> imageList = Arrays.asList(getProductImagesName.split("\\s*,\\s*"));
		//	int numberOfImage = imageList.size();

			/*String PATHS = "src/main/resources/static/image/product/";
		    
			String folderNames =  PATHS.concat(Long.toString(product.getId()));
			File folder = new File(folderNames);*/
			for(String image : imageList) {
				if(!removeCover && product.getCoverImageName().equalsIgnoreCase(image)) {
					System.out.println("Removing All Images except Cover Image "+image);
				}else {
					 fileUrl = endpointUrl + "/" + bucketName + "/" + image;
					 amazonClient.deleteFileFromS3Bucket(fileUrl);
					 System.out.println("Removing All Images "+fileUrl);


				}

				}
				
			}
		
		productService.save(product);
		
		int count =1;
		List<MultipartFile> files = product.getProductImage();
		//MultipartFile productImage = product.getProductImage();
		
		if (null != files && files.size() > 0 && !files.isEmpty()) 
        {
			String imagesName =null;
            for (MultipartFile multipartFile : files) {
            	
            	try {
					byte[] bytes = multipartFile.getBytes();
					
					//To generate random number 50 is max and 10 is min
					Random rand = new Random();
					int  newrandom = rand.nextInt(50) + 10;
					
					/*Using Product Id with Time Stamp and Random Number for File name so we can 
					  have unique file always within product id folder*/
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					String newFileName = product.getId()+timestamp.getTime()+newrandom+".png";
					
					//

					if(count == 1) {
						if(!removefile) {
							if(product.getCoverImageName().equalsIgnoreCase(newFileName) && !removeCover) {

								if(productImageName != null) {
									productImageName = product.getCoverImageName()+","+productImageName;
								}else {
									productImageName = product.getCoverImageName();
								}
								

							}else {
								productImageName = product.getProductImagesName()+","+newFileName;
							}
						if(removeCover) {
							product.setCoverImageName(newFileName);
						}else {
							newFileName = product.getCoverImageName();
							product.setCoverImageName(newFileName);
						}

		
						}else {
							if(product.getCoverImageName().equalsIgnoreCase(newFileName) && !removeCover) {
								productImageName = product.getCoverImageName();
							}else {
								productImageName = newFileName;
							}
							
						}

					count++;
					}else {
						if(!removefile) {
							if(product.getCoverImageName().equalsIgnoreCase(newFileName) && !removeCover) {
							//	productImageName = product.getCoverImageName();
								imagesName = imagesName+newFileName+",";
								productImageName = productImageName+","+newFileName;
							}else {
								imagesName = imagesName+newFileName+",";
								productImageName = productImageName+","+newFileName;
							}
							
						}
						
					}
			
					String PATH = "src/main/resources/static/image/product/";
				    
					String folderName =  PATH.concat(Long.toString(product.getId()));
					
					//Create Folder with product ID as name
					File directory = new File(folderName);
				    if (! directory.exists()){
				        directory.mkdir();     
				    }
					 
					 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(folderName+"/"+newFileName)));
					 stream.write(bytes);
					 stream.close();
					 
            	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
		

		
	//	product.setCoverImageName(product.getCoverImageName());
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
