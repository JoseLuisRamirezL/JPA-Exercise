package com.pepe.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.shoppingcart.dto.OrderHistoryRequest;
import com.pepe.shoppingcart.model.OrderHistory;
import com.pepe.shoppingcart.services.OrderHistoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderHistoryController {
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	
	@PostMapping("/order/add")
	public OrderHistory addOrder(@RequestBody OrderHistoryRequest request) {
		return orderHistoryService.createOrder(request.getUserId()); 
	}
	
	@PutMapping("/order/products/update")
	public OrderHistory updateProducts(@RequestBody OrderHistoryRequest request) {
		return orderHistoryService.addProducts(request.getId(), request.getProductId());
	}
}
