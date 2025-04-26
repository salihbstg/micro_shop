package com.bastug.cart_service.controller;

import com.bastug.cart_service.dto.CartDto;
import com.bastug.cart_service.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestParam(name = "userId") Long userId, @RequestParam(name = "productId") Long productId) {
        cartService.addProductToCart(userId,productId);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeProductFromCart(@RequestParam(name = "userId") Long userId, @RequestParam(name = "productId") Long productId){
        cartService.deleteProductInCart(userId,productId);
        return ResponseEntity.noContent().build();
    }
}
