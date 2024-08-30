package com.pepe.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepe.shoppingcart.exception.UserNotFoundException;
import com.pepe.shoppingcart.model.Products;
import com.pepe.shoppingcart.model.Users;
import com.pepe.shoppingcart.model.WishList;
import com.pepe.shoppingcart.repository.ProductsRepository;
import com.pepe.shoppingcart.repository.UsersRepository;
import com.pepe.shoppingcart.repository.WishListRepository;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishlistRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ProductsRepository productRepository;

    public WishList createWishlist(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        WishList wishlist = new WishList();
        wishlist.setUser(user);

        return wishlistRepository.save(wishlist);
    }
    
    public WishList addProducts(Long wishListId, Long productId) {
    	Products product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException("Product not found"));
    	WishList wishlist = wishlistRepository.findById(wishListId).orElseThrow(() -> new UserNotFoundException("WishList not found"));
    	
    	wishlist.addProduct(product);
    	return wishlistRepository.save(wishlist);
    }
    
    
    public WishList removeProduct(Long wishlistId, Long productId) {
    	WishList wishlist = wishlistRepository.findByIdAndProducts_Id(wishlistId, productId).orElseThrow(() -> new UserNotFoundException("Wishlist or product not found"));
    	Products product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException("Product not found"));
    	wishlist.removeProduct(product);
    	return wishlistRepository.save(wishlist);
    }
    
    public void removeWishList(Long wishlistId) {
    	wishlistRepository.findById(wishlistId).orElseThrow(() -> new UserNotFoundException("WishList not found"));
    	wishlistRepository.deleteById(wishlistId);
    }
}

