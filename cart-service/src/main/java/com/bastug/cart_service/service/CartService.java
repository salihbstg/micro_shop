package com.bastug.cart_service.service;

import com.bastug.cart_service.dto.CartDto;

public interface CartService {
    void addProductToCart(Long userId, Long productId);

    void removeProductFromCart(Long userId, Long productId);

    CartDto getCartByUserId(Long userId);

    void removeAllProductsFromCart(Long userId);
}
