package com.bastug.cart_service.feign;

import com.bastug.cart_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeign {
    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable(name = "id") Long id);
}
