package com.backend.domain.refreshToken.dao;

import com.backend.domain.refreshToken.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // key 값으로 refresh token 삭제
    void deleteByKey(Long key);

    // refresh token 유효기간이 지난 토큰 삭제
    @Modifying
    @Query("delete from RefreshToken t where t.expirationDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
