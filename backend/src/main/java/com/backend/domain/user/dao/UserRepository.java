package com.backend.domain.user.dao;

import com.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // existsByEmail
    boolean existsByEmail(String email);

    // existsByUsername
    boolean existsByUserName(String username);

    // findByEmail
    Optional<User> findByEmail(String email);

}
