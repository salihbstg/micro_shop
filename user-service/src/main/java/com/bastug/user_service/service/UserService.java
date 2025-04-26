package com.bastug.user_service.service;

import com.bastug.user_service.dto.APIResponseDto;
import com.bastug.user_service.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    APIResponseDto getUserByUsername(String username);

    List<APIResponseDto> getAllUsers();

    APIResponseDto getUserByUserId(Long id);

    APIResponseDto getUserByEmail(String email);

    APIResponseDto getUserByTelephone(String telephone);

    UserDto updateUser(UserDto userDto);

    String deleteUser(Long id);

    APIResponseDto deposit(Double amount,Long userId);

    APIResponseDto withdraw(Double amount, Long userId);

    List<APIResponseDto> getUsersWithBalanceAboveLimit(Double limit1,Double limit2);
}
