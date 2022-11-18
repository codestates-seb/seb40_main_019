package com.backend.global.config;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dao.UserRepository;
import com.backend.global.config.auth.filter.JwtAuthenticationFilter;
import com.backend.global.config.auth.filter.JwtVerificationFilter;
import com.backend.global.config.auth.handler.CustomOAuth2UserService;
import com.backend.global.config.auth.handler.OAuth2MemberSuccessHandler;
import com.backend.global.utils.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//@AllArgsConstructor
@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;

    private final UserService userService;

    private final UserRepository userRepository;

    private final CustomOAuth2UserService customOAuth2UserService;

    @Value("${address.front-local}")
    private String FRONT_LOCAL;

    @Value("${address.front-s3}")
    private String FRONT_REMOTE;

    @Value("${address.domain}")
    private String DOMAIN;

    @Value("${address.local}")
    private String LOCAL;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests(authroize -> authroize
//                        todo uri 인증 권한 설정
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> {
                    oauth2.userInfoEndpoint().userService(customOAuth2UserService);
                    log.info("customOAuth2UserService 완료하고 다시 filterChain 진입");
                    oauth2.successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, userRepository));
                })
                .build();


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(FRONT_LOCAL);
        configuration.addAllowedOrigin(FRONT_REMOTE);
        configuration.addAllowedOrigin(DOMAIN);
        configuration.addAllowedOrigin(LOCAL);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, userService, jwtTokenizer, userRepository);
            jwtAuthenticationFilter.setFilterProcessesUrl("/users/login");
//            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler(refreshTokenRepository));
//            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, userRepository);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
