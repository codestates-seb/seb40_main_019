package com.backend.domain.user.application;

import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.UserPatchDto;
import com.backend.domain.user.dto.UserResponseDto;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.domain.user.exception.UserNameDuplication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public Long update(Long memberId, UserPatchDto userPatchDto) {

        if (userRepository.existsByUserName((userPatchDto.getUsername()))) {
            throw new UserNameDuplication();
        }

        String encryptedPassword = passwordEncoder.encode(userPatchDto.getPassword());

        User user = userRepository.findById(memberId).orElseThrow(MemberNotFound::new);

        user.patch(userPatchDto, encryptedPassword);

        return memberId;
    }

    @Transactional(readOnly = true)
    public UserResponseDto getMemberInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(MemberNotFound::new);
        return UserResponseDto.toResponse(user);
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기


    public UserResponseDto getMyInfo(Long memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);
        return UserResponseDto.toResponse(user);

    }

}
