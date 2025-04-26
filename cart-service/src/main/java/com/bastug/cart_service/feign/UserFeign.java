package com.bastug.cart_service.feign;

import com.bastug.cart_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {
    @GetMapping("/api/users/id/{id}")
    UserDto getUserById(@PathVariable(name = "id") Long id);
}
