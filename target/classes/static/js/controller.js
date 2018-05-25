/**
 * Created by Samima on 1/22/2017.
 */

var cartApp = angular.module("cartApp", []);

cartApp.controller("cartCtrl", function ($scope, $http) {

    $scope.refreshCart = function () {
        $http.get('/adminportal/rest/cart/'+$scope.cartId).success(function (data) {
            $scope.shoppingCart = data;
            $scope.shoppingCart.shippingCost = $scope.calShipping(data);
        });
    };

    $scope.clearCart = function () {
        $http.delete('/adminportal/rest/cart/'+$scope.cartId).success($scope.refreshCart());

    };

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function (productId) {
        $http.put('/adminportal/rest/cart/add/'+productId).success(function () {
            $(function()
            {
                $('.overlay_'+productId).show();
                setTimeout(function() {
                    $('.overlay_'+productId).fadeOut('fast');
                }, 3000);
            });
        });
    };
    ////
    $scope.updateCartItem = function (cartItemId) {
    	$('.updateCartitem').hide();
    	$('.spinnerspin').ploading({
    	    action: 'show', 
    	    spinner: 'wave'
    	  })
    	var qty = $('#'+cartItemId).val();
        $http.put('/shoppingCart/updateCartItem/'+cartItemId+'/'+qty).success(function (data) {
        	$scope.shoppingCart = data;
        	 $scope.shoppingCart.shippingCost = $scope.calShipping(data);
             $('.updateCartitem').show();
             $('.spinnerspin').ploading({
            	    action: 'hide'
            })
        });
    };
////
    $scope.removeFromCart = function (cartItemId) {
    	
        $http.post('/rest/cart/remove/'+cartItemId).success(function (data) {
        	$scope.shoppingCart = data;
       	 	$scope.shoppingCart.shippingCost = $scope.calShipping(data);
            
        });
    };


   
    
    $scope.calShipping = function (data) {
    	$scope.shoppingCart = data;
        var shippingTotal = $scope.shoppingCart.shippingCost;
        if (shippingTotal == 0){
        	shippingTotal = 'FREE';
        }
        return shippingTotal;

    };
    
   
    
    
    //Promo code logic
    $scope.applyPromo = function () {
    	
    	  var id = $('#cartId').val();
	      var promocode = $('#enterPromoCode').val();
	      if(promocode ==""){
	    	  	$('#applyPromoError').hide();
				$('.applyPromoError').html("Please enter a valid promo code");
				//$('#applyPromoError').css('display','inline-block');
			}else{
				$('.applyPromoError').html("");
				$('.spinnerspin').ploading({
		    	    action: 'show', 
		    	    spinner: 'wave'
		    	  })
		        $http.put('/shoppingCart/applyPromoCode/'+id+'/'+promocode,{ ajax : 'true'}).success(function (data) {
		        	$scope.shoppingCart = data;
		        	$scope.shoppingCart.shippingCost = $scope.calShipping(data);
		        	 if($scope.shoppingCart.errors == null){
		        		 $('.applyPromoError').html('<p class="text-success"><span>You saved $</span><span>'+$scope.shoppingCart.discountedAmount.toFixed(2)+'</span> using promo code <span>'+$scope.shoppingCart.promoCode.toUpperCase()+'</span>');
		        		 $('#applyPromoNow').hide();
                		 $('#removePromoNow').show();
		        		 $("#enterPromoCode").prop("disabled",true);
		        	 }
		        	$('.spinnerspin').ploading({
		        	    action: 'hide'
		        })
		        $('#applyPromoError').show();
		           /* $(function()
		            {
		            //    $('#applyPromoError').show();
		                setTimeout(function() {
		                    $('#applyPromoError').fadeOut('fast');
		                }, 3000);
		            });*/
		        	
		            
		        });
    }
    };

    $scope.removePromo = function () {
    	var cart = $('#cartId').val();
    	var bagId =$('#bagId').val();
    	$('.applyPromoError').html("");
		$('.spinnerspin').ploading({
    	    action: 'show', 
    	    spinner: 'wave'
    	  })
        $http.post('/shoppingCart/removePromoCode/'+cart+'/'+bagId).success(function (data) {
        	$scope.shoppingCart = data;
        	$scope.shoppingCart.shippingCost = $scope.calShipping(data);
        	$("#enterPromoCode").val("");
        	$("#enterPromoCode").prop("disabled",false);
        	$('#removePromoNow').hide();
        	$('#applyPromoNow').show();
        	$('.spinnerspin').ploading({
        	    action: 'hide'
        	})
   		 	
          //  $scope.refreshCart();
        });
    };
    
    
    

});

/*if($('input[name=stitching]:checked').val() != "readytowear"){
	  size : "unstiched";
}else{
	  size : $('#size').val();
},*/

$(function() {
	var sizes = "";
	$('#addthisproduct').click(
	        function() {
	        	if($('input[name=stitching]:checked').val() != "readytowear"){
	        		  sizes = "unstiched";
	        	}else{
	        		  sizes = $('#size').val();
	        	}	
	        	
	            $.post("/shoppingCart/addItem", {
	            	id : $('#id').val(),
	            	size : sizes,
	                qty : $('#qty').val(),
	                ajax : 'true'
	            }, function(data) {
	                var html = '';
	                var len = data.length;
	                if(len != 0){
	                $('#serverRespone').show();
	                $('#serverRespone').html('<i class="fa fa-check" aria-hidden="true" style="color: forestgreen"></i> <span style="color: forestgreen;">Added to cart.</span>');	
	                $('#serverRespones').show();
	                $('#serverRespones').html('<span><a style="color:red;" href="shoppingCart/cart">View cart</a></span>');	
	                
	                }else{	
	                	$('#serverRespone').show();
		                $('#serverRespone').html('<i class="fa fa-exclamation-circle text-danger" aria-hidden="true"></i> <span class="text-danger">Not Enough Quantity.</span>');	
		                $('#serverRespones').hide();
	                }                
	            });
	            return false;
	        });
  });