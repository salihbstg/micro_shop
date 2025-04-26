package com.bastug.user_service.service.impl;

import com.bastug.user_service.dto.APIResponseDto;
import com.bastug.user_service.dto.UserDto;
import com.bastug.user_service.entity.User;
import com.bastug.user_service.mapper.UserMapper;
import com.bastug.user_service.repository.UserRepository;
import com.bastug.user_service.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.save(UserMapper.userDtoToUser(userDto));
        return UserMapper.userToUserDto(user);
    }

    @Override
    public List<APIResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<APIResponseDto> responseDtoList = new ArrayList<>();
        userList.forEach(user -> {
            responseDtoList.add(UserMapper.userToAPIResponseDto(user));
        });
        return responseDtoList;
    }

    @Override
    public APIResponseDto getUserByUsername(String username) {
        return UserMapper.userToAPIResponseDto(userRepository.findByUsername(username));
    }


    @Override
    public APIResponseDto getUserByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserMapper.userToAPIResponseDto(user.get());
        }
        return null;
    }

    @Override
    public APIResponseDto getUserByEmail(String email) {
        return UserMapper.userToAPIResponseDto(userRepository.findByEmail(email));
    }

    @Override
    public APIResponseDto getUserByTelephone(String telephone) {
        return UserMapper.userToAPIResponseDto(userRepository.findByPhone(telephone));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if (userDto.id() != null) {
            Optional<User> optionalUser = userRepository.findById(userDto.id());
            if (optionalUser.isPresent()) {
                User user = UserMapper.userDtoFillUserForUpdate(optionalUser.get(), userDto);
                return UserMapper.userToUserDto(userRepository.save(user));
            }
        }
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted";
        }
        return null;
    }

    @Override
    public APIResponseDto deposit(Double amount, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBalance(user.getBalance() + amount);
            return UserMapper.userToAPIResponseDto(userRepository.save(user));
        }
        return null;
    }

    @Override
    public APIResponseDto withdraw(Double amount, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBalance(user.getBalance() - amount);
            return UserMapper.userToAPIResponseDto(userRepository.save(user));
        }
        return null;
    }

    @Override
    public List<APIResponseDto> getUsersWithBalanceAboveLimit(Double limit1, Double limit2) {
        List<User> users = userRepository.findByBalanceBetween(limit1, limit2);
        List<APIResponseDto> responseDtoList = new ArrayList<>();
        users.forEach(user -> {
            responseDtoList.add(UserMapper.userToAPIResponseDto(user));
        });
        return responseDtoList;
    }
}
