package com.bastug.order_service.feign;

import com.bastug.order_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeign {
    @PostMapping("/api/products/getProductsByIds")
    List<ProductDto> findProductsByProductIdList(@RequestBody List<Long> productIdList);
}
