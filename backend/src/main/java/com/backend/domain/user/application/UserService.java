package com.backend.domain.user.application;

import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.exception.TokenNotFound;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.ReissueResponseDto;
import com.backend.domain.user.dto.UserPatchDto;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.domain.user.exception.UserNameDuplication;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;
import com.backend.global.utils.jwt.JwtTokenizer;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenRepository refreshTokenRepository;


    public Long update(Long memberId, UserPatchDto userPatchDto) {

        if (userRepository.existsByUserName((userPatchDto.getUsername()))) {
            throw new UserNameDuplication();
        }

        String encryptedPassword = passwordEncoder.encode(userPatchDto.getPassword());

        User user = userRepository.findById(memberId).orElseThrow(MemberNotFound::new);

        user.patch(userPatchDto, encryptedPassword);

        return memberId;
    }

    public User getLoginUser() { //로그인된 유저가 옳바른 지 확인하고 정보 가져옴
        return findUser(getUserByToken());
    }

    private User findUser(User user) {// 아래 getUserByToken 쓸거임
        return findVerifiedUser(user.getUserId());
    }

    public User findVerifiedUser(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        User findUser = optionalUser.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        if (findUser.getUserStatus() == User.UserStatus.USER_NOT_EXIST) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        return findUser;
    }

    @Transactional
    public User createUser(User user) {
        // 현재 활동중인 일반 회원가입으로 가입한 유저의 이미 등록된 이메일인지 확인
        verifyExistsEmailByOriginal(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        return userRepository.save(user);

    }

    private void verifyExistsEmailByOriginal(String email) { // 현재 활동중인 일반 회원가입으로 가입한 유저의 이미 등록된 이메일인지 확인
        Optional<User> user = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, "original");
        if (user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
    }

    public void verifyExistUserByEmailAndOriginal(String email) { //현재 활동중인 일반 회원가입으로 가입한 유저중 email 파라미터로 조회
        Optional<User> user = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, "original");
        if (user.isEmpty()) {//DB에 없는 유저거나 이전에 탈퇴한 유저면 예외처리함
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
    }

    public User getUserByToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails memberDetails = (CustomUserDetails) principal;

        return memberDetails.getUser();
    }

    @Transactional
    public User updateUser(User user) {
        User findUser = findVerifiedUser(user.getUserId());
//        Optional.ofNullable(user.getUpdatedAt())//업데이트 날짜 수정
//                .ifPresent(userUpdatedAt ->findUser.setUpdatedAt(userUpdatedAt));

        Optional.ofNullable(user.getProfileImage())//유저 프로필이미지 수정
                .ifPresent(findUser::setProfileImage);

        Optional.ofNullable(user.getUserName())//유저 닉네임 수정
                .ifPresent(findUser::setUserName);

        Optional.ofNullable(user.getEmail())//유저 이메일 수정
                .ifPresent(findUser::setEmail);

        Optional.ofNullable(user.getUserStatus())//유저 탈퇴
                .ifPresent(findUser::setUserStatus);

        Optional.ofNullable(user.getUserRole())//유저 권한 수정
                .ifPresent(findUser::setUserRole);

        return findUser;
    }

    public ReissueResponseDto createAccessToken(String refreshToken, HttpServletResponse response) {
        Map<String, Object> claims = null;
        User user = null;

        try {
            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getRefreshSecretKey());
            claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();
            // 여기서 기억해야 할 부분은 JWT에서 Claims를 파싱할 수 있다는 의미는 내부적으로 서명(Signature) 검증에 성공했다는 의미
            System.out.println(claims);
            Long userId = Long.parseLong(claims.get("userId").toString());
            user = userRepository.findById(userId).get();
        } catch (SignatureException se) {
            throw new JwtException("사용자 인증 실패");
        } catch (ExpiredJwtException ee) {
            throw new JwtException("토큰 기한 만료");
        } catch (Exception e) {
            throw e;
        }


        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());
        String accessToken = "Bearer " + jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        response.setHeader("Authorization", accessToken);

        return ReissueResponseDto.toResponse(user);
    }

    @Transactional
    public void logout(String refreshToken, HttpServletResponse response) {

        refreshToken = Optional.ofNullable(refreshToken)
                .orElseThrow(TokenNotFound::new);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(0)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        Map<String, Object> claims = null;
        User user = null;

        try {
            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getRefreshSecretKey());
            claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();
            Long userId = Long.parseLong(claims.get("userId").toString());
            user = userRepository.findById(userId).get();
        } catch (SignatureException se) {
            throw new JwtException("사용자 인증 실패");
        } catch (ExpiredJwtException ee) {
            throw new JwtException("토큰 기한 만료");
        } catch (Exception e) {
            throw e;
        }

        refreshTokenRepository.deleteByKey(user.getUserId());
    }

}
