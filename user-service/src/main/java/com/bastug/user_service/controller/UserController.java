package com.bastug.user_service.controller;

import com.bastug.user_service.dto.APIResponseDto;
import com.bastug.user_service.dto.UserDto;
import com.bastug.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PostMapping("{userId}/deposit")
    public ResponseEntity<APIResponseDto> deposit(@RequestParam(name = "amount") Double amount, @PathVariable(name="userId") Long userId) {
        return ResponseEntity.ok(userService.deposit(amount, userId));
    }

    @PostMapping("{userId}/withdraw")
    public ResponseEntity<APIResponseDto> withdraw(@RequestParam(name = "amount") Double amount, @PathVariable(name="userId") Long userId) {
        return ResponseEntity.ok(userService.withdraw(amount,userId));
    }

    @GetMapping
    public ResponseEntity<List<APIResponseDto>> getAllUsers( ) {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<APIResponseDto> getUserByUsername(@PathVariable(name = "username") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/id/{id}")
    ResponseEntity<APIResponseDto> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserByUserId(id));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<APIResponseDto> getUserByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/phone/{phone}")
    ResponseEntity<APIResponseDto> getUserByPhone(@PathVariable(name = "phone") String phone) {
        return ResponseEntity.ok(userService.getUserByTelephone(phone));
    }

    @GetMapping("/balance_limit")
    ResponseEntity<List<APIResponseDto>> getUsersWithBalanceAboveLimit(@RequestParam(name = "min") Double min, @RequestParam(name = "max") Double max) {
        return ResponseEntity.ok(userService.getUsersWithBalanceAboveLimit(min,max));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
