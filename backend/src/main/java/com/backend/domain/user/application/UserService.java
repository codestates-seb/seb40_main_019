package com.backend.domain.user.application;

import com.backend.domain.point.application.PointService;
import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.PasswordDto;
import com.backend.domain.user.dto.TestUserResponseDto;
import com.backend.domain.user.dto.UserLoginResponseDto;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;
import com.backend.global.utils.jwt.JwtTokenizer;
import com.google.gson.JsonObject;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PointService pointService;

    private Long guestId = 1L;
    private Long adminId = 1L;

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
        verifyExistsNicknameByOriginal(user.getNickname());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        pointService.addCash(user, 1000000);


        return userRepository.save(user);

    }

    private void verifyExistsEmailByOriginal(String email) { // 현재 활동중인 일반 회원가입으로 가입한 유저의 이미 등록된 이메일인지 확인
        Optional<User> user = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, "original");
        if (user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATION);
        }
    }

    private void verifyExistsNicknameByOriginal(String nickname) { //중복닉네임인지 확인
        Optional<User> user = userRepository.findByNicknameAndUserStatusAndSocialLogin(nickname, User.UserStatus.USER_EXIST, "original");
        if (user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.NICKNAME_DUPLICATION);
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

        Optional.ofNullable(user.getModifiedAt())
                .ifPresent(findUser::setModifiedAt);

        Optional.ofNullable(user.getNickname())
                .ifPresent(findUser::setNickname);

        Optional.ofNullable(user.getProfileImage())
                .ifPresent(findUser::setProfileImage);

//        Optional.ofNullable(user.getAbout())
//                .ifPresent(findUser::setAbout);

        Optional.ofNullable(user.getUserStatus())
                .ifPresent(findUser::setUserStatus);

        Optional.ofNullable(user.getZipCode())
                .ifPresent(findUser::setZipCode);

        Optional.ofNullable(user.getAddress())
                .ifPresent(findUser::setAddress);

        Optional.ofNullable(user.getPhone())
                .ifPresent(findUser::setPhone);

        Optional.ofNullable(user.getUsername())
                .ifPresent(findUser::setUsername);

        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> findUser.setPassword(passwordEncoder.encode(password)));


        return findUser;
    }

    /**
     * Refresh Token으로 Access Token 재발급
     *
     * @param refreshToken 재발급 요청한 유저의 Refresh Token
     * @param response     재발급한 Access Token을 Response Header에 담기 위한 HttpServletResponse
     * @return 재발급 받은 User 정보
     */
    public UserLoginResponseDto createAccessToken(String refreshToken, HttpServletResponse response) {
        User user = getUser(refreshToken);

        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMillisecond());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        Map<String, Object> claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();

        String accessToken = "Bearer " + jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        log.info("재발급 : accessToken 생성완료 {}", accessToken);
        log.info("accessToken : {}", accessToken);

        response.setHeader("Authorization", accessToken);

        return UserLoginResponseDto.toResponse(user);
    }

    /**
     * UserId로 유저, RefreshToken 조회 후 토큰 삭제
     *
     * @param userId 유저 ID
     */
    @Transactional
    public void logout(Long userId) {
        findVerifiedUser(userId);
        log.info("로그아웃: {}", userId);
        findVerifiedRefreshToken(userId);
        refreshTokenRepository.deleteByKey(userId);
    }

    private void findVerifiedRefreshToken(Long userId) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findById(userId);

        RefreshToken findUser = optionalRefreshToken.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ALRREADY_LOGOUT));
    }


    private User getUser(String refreshToken) {
        Map<String, Object> claims;
        User user;
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
        return user;
    }

    @Transactional
    public TestUserResponseDto signupTestAccount(String userRole) {

//        String randomEmail = createTestAccountEmail();
//        String randomUsername = createTestAccountUsername();

        String testRole = userRole;
        String testUserId = "";

        if (Objects.equals(userRole, "ROLE_USER_TEST")) {
            testRole = "guest";
            testUserId = String.valueOf(guestId);
            hitGuest();
        } else {
            testRole = "admin";
            testUserId = String.valueOf(adminId);
            hitAdmin();
        }

        String guestEmail = testRole + testUserId + "@test.com";
        String guestNickname = testRole + testUserId;

        String randomPassword = createTestAccountPassword();
        String testAddress = "서울특별시 강남구 테헤란로 427";
        String zipCode = "16164";

        User testUser = User.builder()
                .email(guestEmail)
                .nickname(guestNickname)
                .password(randomPassword)
//                .about("안녕하세요. 테스트 계정입니다.")
                .userRole(userRole)
                .profileImage("https://i.ibb.co/7bQQYkX/kisspng-computer-icons-user-profile-avatar-5abcbc2a1f4f51-20180201102408184.png")
                .socialLogin("original")
                .address(testAddress)
                .zipCode(zipCode)
                .build();

        testUser.encodePassword(passwordEncoder);

        testUser.addCash(1000000);

        userRepository.save(testUser);

        return TestUserResponseDto.builder()
                .email(testUser.getEmail())
                .password(randomPassword)
                .build();
    }

    // 테스트용 계정 비밀번호 생성
    private String createTestAccountPassword() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) (rnd.nextInt(26)) + 97);
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) (rnd.nextInt(26)) + 65);
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    private void hitGuest() {
        guestId++;
    }

    private void hitAdmin() {
        adminId++;
    }

    public String getLoginUserInfo(User user) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("nickname", user.getNickname());
        jsonObject.addProperty("imageUrl", user.getProfileImage());
        jsonObject.addProperty("userRole", user.getUserRole());

        return jsonObject.toString();
    }

    public void deleteUser(User user) {
        user.setUserStatus(User.UserStatus.USER_NOT_EXIST);
        refreshTokenRepository.deleteByKey(user.getUserId());
        userRepository.save(user);
    }

    public boolean confirmUserPassword(User user, PasswordDto password) {
        return passwordEncoder.matches(password.getPassword(), user.getPassword());
    }
}
