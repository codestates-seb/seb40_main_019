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
public class MemberDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("MemberDetailsService : 진입");
        Optional<User> userEntity  = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST,"original");

        return new CustomUserDetails(userEntity.get());
    }


}
