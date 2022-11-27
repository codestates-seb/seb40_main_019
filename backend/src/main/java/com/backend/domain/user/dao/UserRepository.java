package com.backend.domain.user.dao;

import com.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);

    Optional<User> findByEmailAndUserStatusAndSocialLogin(String email,User.UserStatus userStatus,String socialLogin);

    Optional<User> findByNicknameAndUserStatusAndSocialLogin(String nickname, User.UserStatus userExist, String original);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phoneNumber);
    void deleteAllByUserRoleOrUserRole(String guestUserRole, String guestAdminRole);
}
