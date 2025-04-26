package com.bastug.product_service.service;

import com.bastug.product_service.dto.ProductDto;

import java.net.URI;
import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByCategories(String category);

    ProductDto getProductById(Long id);

    ProductDto getProductByName(String productName);

    List<ProductDto> getProductByPrice(Double min, Double max);

    ProductDto createProduct(ProductDto productDto);

    void deleteProduct(Long id);

    List<ProductDto> saveProducts(List<ProductDto> productDtoList);

    void deleteProductByName(String name);

    ProductDto updateProduct(ProductDto productDto);

    Double updatePrice(Double delta,Long id);

    Double updateStock(int delta, Long id);
}
