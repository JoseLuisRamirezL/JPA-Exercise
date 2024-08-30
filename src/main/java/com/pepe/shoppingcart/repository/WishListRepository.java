package com.pepe.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pepe.shoppingcart.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
	List<WishList> findByUserId(Long userId);
	Optional<WishList> findByIdAndProducts_Id(Long id, Long productId);
}
