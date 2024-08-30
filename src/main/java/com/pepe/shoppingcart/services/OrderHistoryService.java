package com.pepe.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepe.shoppingcart.exception.NoProductsInventoryException;
import com.pepe.shoppingcart.exception.UserNotFoundException;
import com.pepe.shoppingcart.model.OrderHistory;
import com.pepe.shoppingcart.model.Products;
import com.pepe.shoppingcart.model.Users;
import com.pepe.shoppingcart.repository.OrderHistoryRepository;
import com.pepe.shoppingcart.repository.ProductsRepository;
import com.pepe.shoppingcart.repository.UsersRepository;

@Service
public class OrderHistoryService {

	@Autowired
    private OrderHistoryRepository orderhistoryRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ProductsRepository productRepository;

    public OrderHistory createOrder(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        OrderHistory orderhistory = new OrderHistory();
        orderhistory.setUser(user);

        return orderhistoryRepository.save(orderhistory);
    }
    
    public OrderHistory addProducts(Long orderHistoryId, Long productId) {
    	Products product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException("Product not found"));
    	OrderHistory orderhistory = orderhistoryRepository.findById(orderHistoryId).orElseThrow(() -> new UserNotFoundException("WishList not found"));
    	if(product.getTotalProductsInventory() == 0)
    		throw new NoProductsInventoryException("No more products available, out of stock.");
    	product.setTotalProductsInventory(product.getTotalProductsInventory() - 1);
    	
    	
    	orderhistory.addProduct(product);
    	return orderhistoryRepository.save(orderhistory);
    }
}

