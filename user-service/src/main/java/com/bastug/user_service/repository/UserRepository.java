package com.bastug.user_service.repository;

import com.bastug.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhone(String telephone);

    List<User> findByBalanceBetween(double balance, double balance2);
}
