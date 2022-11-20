package com.backend.global.config.auth.userdetails;

import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 유저 정보를 가져오는 함수
     *
     * @param email 이메일
     * @return 유저 정보
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService : 진입");
        Optional<User> userEntity = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, "original");

        return new CustomUserDetails(userEntity.get());
    }


}
