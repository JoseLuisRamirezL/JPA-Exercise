package com.pepe.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pepe.shoppingcart.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
	Products findByName(String name);
	List<Products> findByPrice(double price);
}
