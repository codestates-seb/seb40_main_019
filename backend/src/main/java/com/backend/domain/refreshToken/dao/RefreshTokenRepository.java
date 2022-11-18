package com.backend.domain.refreshToken.dao;

import com.backend.domain.refreshToken.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // key 값으로 refresh token 삭제
    void deleteByKey(Long key);

}
