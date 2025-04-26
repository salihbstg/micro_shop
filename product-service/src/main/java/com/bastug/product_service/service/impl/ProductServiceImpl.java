package com.bastug.product_service.service.impl;

import com.bastug.product_service.dto.ProductDto;
import com.bastug.product_service.entity.Product;
import com.bastug.product_service.mapper.ProductMapper;
import com.bastug.product_service.repository.ProductRepository;
import com.bastug.product_service.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(ProductMapper.productToProductDto(product));
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductsByCategories(String category) {
        List<Product> products = productRepository.findByCategory(category);
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(ProductMapper.productToProductDto(product));
        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductMapper.productToProductDto(product.get());
        }
        return null;
    }

    @Override
    public ProductDto getProductByName(String productName) {
        return ProductMapper.productToProductDto(productRepository.findByName(productName));
    }

    @Override
    public List<ProductDto> getProductByPrice(Double min, Double max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(ProductMapper.productToProductDto(product));
        }
        return productDtoList;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.productDtoToProduct(productDto);
        productRepository.save(product);
        return ProductMapper.productToProductDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> saveProducts(List<ProductDto> productDtoList) {
        for (ProductDto productDto : productDtoList) {
            Product product = ProductMapper.productDtoToProduct(productDto);
            productRepository.save(product);
        }
        return productDtoList;
    }

    @Override
    public void deleteProductByName(String name) {
        productRepository.deleteByName(name);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        if (productDto.id() != 0) {
            Optional<Product> optionalProduct = productRepository.findById(productDto.id());
            if (optionalProduct.isPresent()) {
                Product product = ProductMapper.productDtoFillProduct(productDto,optionalProduct.get());
                productRepository.save(product);
                return ProductMapper.productToProductDto(product);
            }
        }
        return null;
    }

    @Override
    public Double updatePrice(Double delta,Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(product.getPrice() + delta);
            productRepository.save(product);
            return product.getPrice();
        }
        return null;
    }

    @Override
    public Double updateStock(int delta, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStock(product.getStock() + delta);
            productRepository.save(product);
            return product.getPrice();
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByIds(List<Long> productIdList) {
        List<ProductDto> products=new ArrayList<>();
        for(Long a:productIdList){
            Optional<Product> optionalProduct = productRepository.findById(a);
            optionalProduct.ifPresent(product -> products.add(ProductMapper.productToProductDto(product)));
        }
        return products;
    }
}
