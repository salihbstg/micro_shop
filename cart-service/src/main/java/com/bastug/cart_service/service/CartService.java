package com.bastug.cart_service.service;

import com.bastug.cart_service.dto.CartDto;

import java.util.List;

public interface CartService {
    void addProductToCart(Long userId, Long productId);

    void deleteProductInCart(Long userId, Long productId);

    CartDto getCartByUserId(Long userId);
}
