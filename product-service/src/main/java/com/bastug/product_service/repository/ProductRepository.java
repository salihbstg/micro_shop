package com.bastug.product_service.repository;


import com.bastug.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    Product findByName(String productName);

    List<Product> findByPriceBetween(Double min, Double max);

    void deleteByName(String name);
}
