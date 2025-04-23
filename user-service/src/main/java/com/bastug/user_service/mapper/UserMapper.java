package com.bastug.user_service.mapper;

import com.bastug.user_service.dto.APIResponseDto;
import com.bastug.user_service.dto.UserDto;
import com.bastug.user_service.entity.User;

public class UserMapper {

    public static User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setAddress(userDto.address());
        user.setEmail(userDto.email());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setPassword(userDto.password());
        user.setPhone(userDto.phone());
        user.setBalance(userDto.balance());
        user.setUsername(userDto.username());
        return user;
    }

    public static UserDto userToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getBalance()
        );
    }

    public static APIResponseDto userToAPIResponseDto(User user) {
        return new APIResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getBalance()
        );
    }

    public static User userDtoFillUserForUpdate(User user, UserDto userDto) {
        user.setId(user.getId());
        if(userDto.username() != null) {
            user.setUsername(userDto.username());
        }
        if(userDto.password() != null) {
            user.setPassword(userDto.password());
        }
        if(userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if(userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if(userDto.email() != null) {
            user.setEmail(userDto.email());
        }
        if(userDto.phone() != null) {
            user.setPhone(userDto.phone());
        }
        if(userDto.address() != null) {
            user.setAddress(userDto.address());
        }
        if(userDto.balance() != null) {
            user.setBalance(userDto.balance());
        }

        return user;
    }
}
