package com.pepe.shoppingcart.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class NoProductsInventoryException extends RuntimeException {
	public NoProductsInventoryException(String message) {
		super(message);
	}
}
