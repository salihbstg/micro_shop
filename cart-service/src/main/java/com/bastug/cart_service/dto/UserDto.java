package com.bastug.cart_service.dto;

public record UserDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        Double balance
) {
}
