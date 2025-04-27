package com.bastug.order_service.feign;

import com.bastug.order_service.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CART-SERVICE")
public interface CartFeign {
    @GetMapping("/api/cart")
    CartDto getCart(@RequestParam(name = "userId") Long userId);

    @DeleteMapping("/api/cart/allProductsFromCart/{userId}")
    public ResponseEntity<Void> removeAllProductsFromCart(@PathVariable(name = "userId") Long userId);
}
