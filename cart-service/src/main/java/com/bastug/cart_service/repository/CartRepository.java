package com.bastug.cart_service.repository;

import com.bastug.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserIdAndProductId(Long userId, Long productId);

    List<Cart> findByUserId(Long userId);
}
