package com.backend.domain.user.dao;

import com.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByEmailAndUserStatusAndSocialLogin(String email,User.UserStatus userStatus,String socialLogin);

}
