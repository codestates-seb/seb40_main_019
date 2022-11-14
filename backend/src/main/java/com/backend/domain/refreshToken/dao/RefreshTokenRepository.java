package com.backend.domain.refreshToken.dao;

import com.backend.domain.refreshToken.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // member id 값으로 refresh token 찾기
    Optional<RefreshToken> findByKey(Long key);

    // value 값으로 key 찾기
    Optional<RefreshToken> findByValue(String value);

    // key 값으로 refresh token 삭제
    void deleteByKey(Long key);

    // value 값으로 refresh token 삭제
    void deleteByValue(String value);

}
