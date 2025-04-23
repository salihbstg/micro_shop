package com.bastug.user_service.dto;

public record APIResponseDto(
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
