package com.bastug.product_service.controller;

import com.bastug.product_service.dto.ProductDto;
import com.bastug.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("categories/{category}")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategories(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable(name = "productName") String productName) {
        return ResponseEntity.ok(productService.getProductByName(productName));
    }

    @GetMapping("/between")
    public ResponseEntity<List<ProductDto>> getProductsByBetween(@RequestParam(name = "min") Double min,@RequestParam(name = "max") Double max) {
        return ResponseEntity.ok(productService.getProductByPrice(min,max));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductDto>> createProductBulk(@RequestBody List<ProductDto> productDtoList) {
        return ResponseEntity.ok(productService.saveProducts(productDtoList));
    }

    @PostMapping("/update/price")
    public ResponseEntity<Double> updatePrice(@RequestParam(name = "delta") Double delta,@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.updatePrice(delta,id));
    }

    @PostMapping("/update/stock")
    public ResponseEntity<Double> updateStock(@RequestParam(name = "delta") int delta,@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.updateStock(delta,id));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete_by_name/{name}")
    public ResponseEntity<Void> deleteProductByName(@PathVariable(name = "name") String name) {
        productService.deleteProductByName(name);
        return ResponseEntity.noContent().build();
    }
}
