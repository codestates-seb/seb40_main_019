package com.backend.domain.user.application;


import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("유저 정보가 없습니다."));
//    }
//
//    private UserDetails createUserDetails(User user) {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getUserRole().toString());
//
//        return AuthUser.of(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("MemberDetailsService : 진입");
        Optional<User> userEntity  = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST,"original");

        return new CustomUserDetails(userEntity.get());
    }

}
