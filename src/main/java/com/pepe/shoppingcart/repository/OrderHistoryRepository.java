package com.pepe.shoppingcart.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pepe.shoppingcart.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
