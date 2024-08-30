package com.pepe.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class WishList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private Users user;
	

	@ManyToMany
	@JoinTable(
		        name = "wish_list_products",
		        joinColumns = @JoinColumn(name = "wish_list_id"),
		        inverseJoinColumns = @JoinColumn(name = "product_id")
		    )
	private List<Products> products = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Products> getProduct() {
		return products;
	}

	public void setProduct(List<Products> product) {
		this.products = product;
	}
	
	public void addProduct(Products product) {
        this.products.add(product);
    }
	
	public void removeProduct(Products product) {
		this.products.remove(product);
	}
}