package com.backend.domain.user.application;

import com.backend.domain.point.application.PointService;
import com.backend.domain.point.dao.PointHistoryRepository;
import com.backend.domain.point.domain.PointType;
import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.review.dao.ReviewRepository;
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
import lombok.SneakyThrows;
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
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final PointService pointService;
    private final ReviewRepository reviewRepository;

    private Long guestId;
    private Long adminTestId;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenizer jwtTokenizer, RefreshTokenRepository refreshTokenRepository, PointHistoryRepository pointHistoryRepository, PointService pointService, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenizer = jwtTokenizer;
        this.refreshTokenRepository = refreshTokenRepository;
        this.pointHistoryRepository = pointHistoryRepository;
        this.pointService = pointService;
        this.reviewRepository = reviewRepository;

        guestId = userRepository.countByUserRole("ROLE_USER_TEST") + 1L;
        adminTestId = userRepository.countByUserRole("ROLE_ADMIN_TEST") + 1L;
    }


    public User getLoginUser() { //???????????? ????????? ????????? ??? ???????????? ?????? ?????????
        return findUser(getUserByToken());
    }

    private User findUser(User user) {// ?????? getUserByToken ?????????
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
    public void createUser(User user) {
        log.info("???????????? ??????");
        if (!isNotExistsEmailByOriginal(user.getEmail())) {
            log.info("?????? ??????");
            verifyExistsEmailByOriginal(user.getEmail());
            verifyExistsNicknameByOriginal(user.getNickname());
        } else {
            log.info("???????????? ??????");
            user.setUserStatus(User.UserStatus.USER_EXIST);
            log.info("???????????? ????????? ????????? USER_EXIST??? ??????");
            Optional<User> notExistUser = userRepository.findByEmail(user.getEmail());
            user.setUserId(notExistUser.get().getUserId());
            if (!Objects.equals(notExistUser.get().getNickname(), user.getNickname())) {
                log.info("????????? ?????? ????????? ??????");
                verifyExistsNicknameByOriginal(user.getNickname());
            } else {
                log.info("????????? ?????? ????????? ??????");
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("???????????? ?????????");
        pointService.addCash(user, 1000000, PointType.SignUpPoint);
        log.info("???????????? ????????? ??????");
    }

    private boolean isNotExistsEmailByOriginal(String email) {
        log.info("?????? ?????? ?????? : " + email);
        Optional<User> notExistUser = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_NOT_EXIST, "original");
        return notExistUser.isPresent();
    }

    private void verifyExistsEmailByOriginal(String email) { // ?????? ???????????? ?????? ?????????????????? ????????? ????????? ?????? ????????? ??????????????? ??????
        log.info("????????? ?????? ?????? : " + email);
        Optional<User> existUser = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, "original");
        if (existUser.isPresent()) {
            log.info("?????? ????????? ?????????");
            throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATION);
        }
        log.info("?????? ????????? ?????????");
    }

    private void verifyExistsNicknameByOriginal(String nickname) { //????????????????????? ??????
        log.info("????????? ?????? ?????? : " + nickname);
        Optional<User> user = userRepository.findByNicknameAndUserStatusAndSocialLogin(nickname, User.UserStatus.USER_EXIST, "original");
        if (user.isPresent()) {
            log.info("?????? ????????? ?????????");
            throw new BusinessLogicException(ExceptionCode.NICKNAME_DUPLICATION);
        }
        log.info("?????? ????????? ?????????");
    }

    public void verifyExistUserByEmailAndOriginal(String email) { //?????? ???????????? ?????? ?????????????????? ????????? ????????? email ??????????????? ??????
        Optional<User> user = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, "original");
        if (user.isEmpty()) { //DB??? ?????? ???????????? ????????? ????????? ????????? ???????????????
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
    }

    public User getUserByToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails memberDetails = (CustomUserDetails) principal;

        return memberDetails.getUser();
    }

    public void getUserByEmail(String email) {
        log.info("???????????? ?????? ?????? : " + email);
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(
                () ->
                        new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)));
        log.info("?????? ?????? ??????");
    }

    @Transactional
    public void updateUser(User user) {

        User findUser = findVerifiedUser(user.getUserId());
        if (!Objects.equals(user.getNickname(), findUser.getNickname())) {
            verifyExistsNicknameByOriginal(user.getNickname());
        }

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

    }

    /**
     * Refresh Token?????? Access Token ?????????
     *
     * @param refreshToken ????????? ????????? ????????? Refresh Token
     * @param response     ???????????? Access Token??? Response Header??? ?????? ?????? HttpServletResponse
     * @return ????????? ?????? User ??????
     */
    @SneakyThrows
    public UserLoginResponseDto createAccessToken(String refreshToken, HttpServletResponse response) {
        User user = getUser(refreshToken);

        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMillisecond());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        Map<String, Object> claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();

        String accessToken = "Bearer " + jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        log.info("????????? : accessToken ???????????? {}", accessToken);
        log.info("accessToken : {}", accessToken);

        response.setHeader("Authorization", accessToken);

        return UserLoginResponseDto.toResponse(user);
    }

    /**
     * UserId??? ??????, RefreshToken ?????? ??? ?????? ??????
     *
     * @param userId ?????? ID
     */
    @Transactional
    public void logout(Long userId) {
        findVerifiedUser(userId);
        log.info("????????????: {}", userId);
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
            throw new JwtException("????????? ?????? ??????");
        } catch (ExpiredJwtException ee) {
            throw new JwtException("?????? ?????? ??????");
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
            testUserId = String.valueOf(adminTestId);
            hitAdmin();
        }

        String guestEmail = testRole + testUserId + "@test.com";
        String guestNickname = testRole + testUserId;

        String randomPassword = createTestAccountPassword();
        String testAddress = "??????????????? ????????? ???????????? 427";
        String zipCode = "16164";

        User testUser = User.builder()
                .email(guestEmail)
                .nickname(guestNickname)
                .password(randomPassword)
//                .about("???????????????. ????????? ???????????????.")
                .userRole(userRole)
                .profileImage("https://i.ibb.co/7bQQYkX/kisspng-computer-icons-user-profile-avatar-5abcbc2a1f4f51-20180201102408184.png")
                .socialLogin("original")
                .address(testAddress)
                .zipCode(zipCode)
                .build();

        testUser.encodePassword(passwordEncoder);

        pointService.addCash(testUser, 1000000, PointType.SignUpPoint);
        log.info("???????????? ????????? ??????");

        return TestUserResponseDto.builder()
                .email(testUser.getEmail())
                .password(randomPassword)
                .build();
    }

    // ???????????? ?????? ???????????? ??????
    private String createTestAccountPassword() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3); // 0~2 ?????? ??????, rnd ?????? ????????? ?????? switch ?????? ?????????

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
        adminTestId++;
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
        log.info("?????? ?????? : {}", user.getEmail());

        if (user.getSocialLogin().equals("original")) {
            user.setUserStatus(User.UserStatus.USER_NOT_EXIST);
            refreshTokenRepository.deleteByKey(user.getUserId());
//            pointRepository.deleteByUser(user);
            userRepository.save(user);
        } else {
            log.info("?????? ????????? ???????????? : {}", user.getEmail());
            pointHistoryRepository.deleteByUser(user);
            refreshTokenRepository.deleteByKey(user.getUserId());
            userRepository.delete(user);
        }
        log.info("?????? ?????? ?????? : {}", user.getEmail());
    }

    public boolean comparePassword(User user, PasswordDto password) {
        return user.comparePassword(passwordEncoder, password.getPassword());
    }

    public void newPassword(String email, String newPassword) {
        log.info("??? ???????????? ?????? : {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        user.changePassword(newPassword);
        log.info("???????????? ?????? ??????");
        user.encodePassword(passwordEncoder);
        log.info("???????????? ????????? ??????");
        userRepository.save(user);
        log.info("?????? ???????????? ?????? ?????? : {}", email);
    }

    public String findIdByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhone(phoneNumber).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        String email = user.getEmail();

        return email;
    }

    @Transactional
    public void deleteGustAccount() {
        reviewRepository.deleteByUser_UserRoleOrUser_UserRole("ROLE_USER_TEST", "ROLE_ADMIN_TEST");
        pointHistoryRepository.deleteByUser_UserRoleOrUser_UserRole("ROLE_USER_TEST", "ROLE_ADMIN_TEST");
        userRepository.deleteAllByUserRoleOrUserRole("ROLE_USER_TEST", "ROLE_ADMIN_TEST");
    }
}
