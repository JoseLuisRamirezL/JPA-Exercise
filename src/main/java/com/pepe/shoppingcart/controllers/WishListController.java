package com.pepe.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.shoppingcart.dto.WishListRequest;
import com.pepe.shoppingcart.model.WishList;
import com.pepe.shoppingcart.services.WishListService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WishListController {
	@Autowired
	private WishListService wishListService;
		
	@PostMapping("/wishlist/add")
	public WishList addProductsToWishList(@RequestBody WishListRequest request) {
		return wishListService.createWishlist(request.getUserId()); 
	}
	
	@PutMapping("/wishlist/products/update")
	public WishList updateProducts(@RequestBody WishListRequest request) {
		return wishListService.addProducts(request.getId(), request.getProductId());
	}
	
	@DeleteMapping("/wishlist/{id}/remove/{pid}")
	public WishList removeProduct(@PathVariable Long id, @PathVariable Long pid) {
		return wishListService.removeProduct(id, pid);
	}
	
	@DeleteMapping("/wishlist/remove/{id}")
	public void removeWishList(@PathVariable Long id) {
		wishListService.removeWishList(id);	
	}
}
