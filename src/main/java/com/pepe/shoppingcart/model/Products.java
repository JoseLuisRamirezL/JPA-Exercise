package com.pepe.shoppingcart.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int totalProductsInventory;
	private double price;
	private String description;
	private String image;
	private boolean productStatus;

	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	private Set<OrderHistory> orderHistory = new HashSet<>();

	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	private Set<WishList> wishLists = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalProductsInventory() {
		return totalProductsInventory;
	}

	public void setTotalProductsInventory(int totalProductsInventory) {
		this.totalProductsInventory = totalProductsInventory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<WishList> getWishLists() {
		return wishLists;
	}

	public void setWishLists(Set<WishList> wishLists) {
		this.wishLists = wishLists;
	}

	public Set<OrderHistory> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(Set<OrderHistory> orderHistory) {
		this.orderHistory = orderHistory;
	}
}
