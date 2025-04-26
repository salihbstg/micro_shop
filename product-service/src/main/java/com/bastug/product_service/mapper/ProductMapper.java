package com.bastug.product_service.mapper;

import com.bastug.product_service.dto.ProductDto;
import com.bastug.product_service.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ProductMapper {
    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getColor(),
                product.getStock()
        );
    }

    public static Product productDtoToProduct(ProductDto productDto) {
        return new Product(
                productDto.id(),
                productDto.name(),
                productDto.description(),
                productDto.price(),
                productDto.category(),
                productDto.color(),
                productDto.stock()
        );
    }

    public static Product productDtoFillProduct(ProductDto productDto,Product product) {

        if(productDto.name()!=null) {
            product.setName(productDto.name());
        }
        if(productDto.description()!=null) {
            product.setDescription(productDto.description());
        }
        if(productDto.price()!=null) {
            product.setPrice(productDto.price());
        }
        if(productDto.category()!=null) {
            product.setCategory(productDto.category());
        }
        if(productDto.color()!=null) {
            product.setColor(productDto.color());
        }
        if(productDto.stock()<0){
            product.setStock(productDto.stock());
        }
        return product;
    }
}
