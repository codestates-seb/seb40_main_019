package com.backend.global.config.security.handler;

import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.user.domain.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {

        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        String refreshToken = response.getHeader("Set-Cookie").substring(13).split(";")[0];

        // RefreshToken 저장
        RefreshToken refresh = RefreshToken.builder()
                .key(authUser.getUserId())
                .value(refreshToken)
                .build();

        refreshTokenRepository.save(refresh);
    }

}
