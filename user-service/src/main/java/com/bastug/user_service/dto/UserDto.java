package com.bastug.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record UserDto(
        Long id,
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        Double balance
) {
}
