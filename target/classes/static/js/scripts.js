//Update Sub Category
$(function() {
	$('#category').change(
	        function() {
	            $.getJSON("/adminportal/product/getsubcategories", {
	                id : $(this).val(),
	                ajax : 'true'
	            }, function(data) {
	                var html = '<option value="" selected="selected" disabled="disabled">Please select an option...</option>';
	                var len = data.length;
	                if(len < 1 ){
	                	$('#subCategory').prop( "disabled", true );
	                	$('#mainSubCategory').prop( "disabled", true );
	                }else{
	                	$('#subCategory').prop( "disabled", false );
	                }
	                for ( var i = 0; i < len; i++) {
	                    html += '<option value="' + data[i].id + '">'
	                            + data[i].subCategoryName + '</option>';
	                }
	                html += '</option>';
	                $('#subCategory').html(html);
	                $('#mainSubCategory').html('<option value="" selected="selected" disabled="disabled">Please select an option...</option>');
	              
	            });
	        });
  });
//Update Sub Category
$(function() {
	$('#subCategory').change(
	        function() {
	            $.getJSON("/adminportal/product/getsubsubcategories", {
	                id : $(this).val(),
	                ajax : 'true'
	            }, function(data) {
	                var html = '<option value="" selected="selected" disabled="disabled">Please select an option...</option>';
	                var len = data.length;
	                if(len < 1 ){
	                	$('#mainSubCategory').prop( "disabled", true );
	                }else{
	                	$('#mainSubCategory').prop( "disabled", false );
	                }
	                for ( var i = 0; i < len; i++) {
	                    html += '<option value="' + data[i].id + '">'
	                            + data[i].subSubCategoryName + '</option>';
	                }
	                html += '</option>';
	                $('#mainSubCategory').html(html);
	            });
	        });
  });
//Add New Category
$(function() {
	$('#addnewcategorysubmit').click(
	        function() {
	            $.post("/adminportal/category/addmaincategory", {
	            	categoryName : $('#categoryName').val(),
	                categorySlug : $('#categorySlug').val(),
	                ajax : 'true'
	            }, function(data) {
	                var html = '';
	                var htmls = '<option value="" selected="selected" disabled="disabled">Please select an option...</option>';
	                var len = data.length;
	                for ( var i = 0; i < len; i++) {
	                    htmls += '<option value="' + data[i].id + '">'
	                            + data[i].categoryName + '</option>';
	                }
	                	htmls += '</option>';
	                    html += '<a href="category/id=' + data[len-1].id + '">'+ data[len-1].categoryName + '</a>';
	                $('#categoryList').show();
	                $('#categoryList').html("Category "+html+" has been added sucessfully");
	                $('#categoryName').val('');
	                $('#categorySlug').val('');
	                $('#parentcategory').html(htmls);
	                
	                
	            });
	            return false;
	        });
  });

//Add New Sub Category
$(function() {
	$('#addnewsubcategorysubmit').click(
	        function() {
	            $.post("/adminportal/category/addsubcategory", {
	            	subCategoryName : $('#subCategoryName').val(),
	            	subCategorySlug : $('#subCategorySlug').val(),
	            	parentcategory : $('#parentcategory').val(),
	                ajax : 'true'
	            }, function(data) {
	                var html = '';
	                    html += '<a href="category/id=' + data.id + '">'+ data.subCategoryName + '</a>';
	                $('#subcategoryList').show();
	                $('#subcategoryList').html("Sub Category "+html+" has been added sucessfully");
	                $('#subCategoryName').val();
	                $('#subCategorySlug').val();
	                $('#parentcategory').val();
	              
	            });
	            return false;
	        });
  });

//Add New Sub Sub-Category
$(function() {
	$('#addnewsubsubcategorysubmit').click(
	        function() {
	            $.post("/adminportal/category/addsubsubcategory", {
	            	subCategory : $('#subCategory').val(),
	            	subSubCategoryName : $('#subSubCategoryName').val(),
	            	subSubCategorySlug : $('#subSubCategorySlug').val(),
	                ajax : 'true'
	            }, function(data) {
	                var html = '';
	                    html += '<a href="category/id=' + data.id + '">'+ data.subSubCategoryName + '</a>';
	                $('#subsubcategoryList').show();
	                $('#subsubcategoryList').html("Sub Sub-Category "+html+" has been added sucessfully");
	               
	              
	            });
	            return false;
	        });
  });

/**
 * 
 */
$(document).ready(function(){
	$('.delete-product').on('click', function (){
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'remove';
		/*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this item? It can't be undone.",
			buttons:{
				cancel:{
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm:{
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed){
				if(confirmed){
					$.post(path, {'id':id}, function(res){
						location.reload();
					});
				}
			}
		});
	});
	
	

	
	$('#deleteSelected').click(function(){
		var idList= $('.checkboxProduct');
		var productIdList=[];
		for(var i=0; i< idList.length; i++){
			if(idList[i].checked==true){
				productIdList.push(idList[i]['id'])
			}
		}
		
		console.log(productIdList);
		
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'removeList';
		/*]]>*/
		
		bootbox.confirm({
			message: "Are you sure to remove all selected items? It can't be undone.",
			buttons:{
				cancel:{
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm:{
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed){
				if(confirmed){
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(productIdList),
						contentType: "application/json",
						success: function(res){
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
		
	});
	
	$("#selectAllProducts").click(function(){
		if($(this).prop("checked")==true){
			$(".checkboxProduct").prop("checked",true);
		}else if($(this).prop("checked")==false){
			$(".checkboxProduct").prop("checked",false);
		}
	})
	
});

