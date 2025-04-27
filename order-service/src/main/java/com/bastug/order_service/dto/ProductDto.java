package com.bastug.order_service.dto;

public record ProductDto(
        Long id,
        String name,
        String description,
        Double price,
        String category,
        String color,
        int stock
) {
}
