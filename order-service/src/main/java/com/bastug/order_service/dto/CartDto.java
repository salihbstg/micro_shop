package com.bastug.order_service.dto;

import java.util.List;

public record CartDto(
        UserDto userDto,
        List<ProductDto> productDtoList
) {
}
