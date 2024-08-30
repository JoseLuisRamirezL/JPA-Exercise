package com.pepe.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepe.shoppingcart.model.Products;
import com.pepe.shoppingcart.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);
	List<Users> findByUserName(String userName);
}
