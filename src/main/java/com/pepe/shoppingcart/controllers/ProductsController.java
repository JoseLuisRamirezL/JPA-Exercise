package com.pepe.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.shoppingcart.dto.ProductRequest;
import com.pepe.shoppingcart.model.Products;
import com.pepe.shoppingcart.model.Users;
import com.pepe.shoppingcart.repository.ProductsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductsController {
	private ProductsRepository repository;

	@Autowired
	ProductsController(ProductsRepository repository) {
		this.repository = repository;
	}

	// Create a product
	@RequestMapping(value = "/products", method = RequestMethod.PUT)
	public ResponseEntity<?> saveProduct(@RequestBody ProductRequest request) {
		Products existingProduct = repository.findByName(request.getName());
		if (existingProduct != null) {
			existingProduct.setTotalProductsInventory(existingProduct.getTotalProductsInventory() + 1);
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(existingProduct));
		} else {
			Products product = new Products();
			product.setName(request.getName());
			product.setDescription(request.getDescription());
			product.setImage(request.getImage());
			product.setPrice(request.getPrice());
			product.setProductStatus(true);
			product.setTotalProductsInventory(1);
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
		}
	}

	// Update a product
	@RequestMapping(value = "/products/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest request) {
		Products product = repository.findById(id).orElse(null);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no product with that id.");
		}
		product.setImage(request.getImage());
		product.setPrice(request.getPrice());
		product.setDescription(request.getDescription());
		product.setTotalProductsInventory(request.getTotalProductsInventory());
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
	}

	// Delete a product
	@RequestMapping(value = "/products/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUsers(@PathVariable("id") int id, ProductRequest request) {
		Products product = repository.findById(id).orElse(null);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no product with that id.");
		}
		product.setProductStatus(false);
		repository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product deleted");
	}

	// List of all products
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<?> getAllProducts() {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.findAll());
	}

	// Get a products by name
	@RequestMapping(value = "/products/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductName(@PathVariable("name") String name) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.findByName(name));
	}

	// Get a products by price
	@RequestMapping(value = "/products/price/{price}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductPrice(@PathVariable("price") double price) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.findByPrice(price));
	}

}
