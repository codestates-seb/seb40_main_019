package com.backend.global.config.security.filter;

import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.AuthUser;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.SignUpRequestDto;
import com.backend.domain.user.dto.TokenDto;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.jwt.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        SignUpRequestDto signUpRequestDto = om.readValue(request.getInputStream(), SignUpRequestDto.class);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        AuthUser authUser = (AuthUser) authResult.getPrincipal();

        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(MemberNotFound::new);


        TokenDto tokenDto = tokenProvider.generateTokenDto(authUser);

        String grantType = tokenDto.getGrantType();

        String refreshToken = tokenDto.getRefreshToken();

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        response.setHeader("Authorization", grantType + tokenDto.getAccessToken());

        // response body에 member의 emial, username, ImageUrl을 담아서 보내준다.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"email\":\"" + user.getEmail() + "\"," +
                "\"name\":\"" + user.getUserName() + "\"," +
                "\"imageUrl\":\"" + user.getProfileImage() + "\"}");


        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

}
