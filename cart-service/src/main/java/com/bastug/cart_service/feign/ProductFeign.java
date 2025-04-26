package com.bastug.cart_service.feign;

import com.bastug.cart_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeign {
    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable(name = "id") Long id);

    @PostMapping("/api/products/getProductsByIds")
    public List<ProductDto> findProductsByProductIdList(@RequestBody List<Long> productIdList);
}
