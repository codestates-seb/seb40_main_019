package com.backend.domain.refreshToken.application;

import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    // refresh token 유효기간이 지난 토큰 삭제
    @Transactional
    public void deleteExpiredToken() {
        refreshTokenRepository.deleteAllExpiredSince(Calendar.getInstance().getTime());
    }
}
