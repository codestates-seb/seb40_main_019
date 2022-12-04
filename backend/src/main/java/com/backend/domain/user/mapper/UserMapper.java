package com.backend.domain.user.mapper;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.UserPatchDto;
import com.backend.domain.user.dto.UserPostDto;
import com.backend.domain.user.dto.UserResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    default User userPostDtoToUser(UserPostDto userPostDto) {
        User user = new User();

        user.setEmail(userPostDto.getEmail());
        user.setNickname(userPostDto.getNickname());
        user.setPassword(userPostDto.getPassword());
        user.setUserRole("ROLE_USER");
        user.setProfileImage("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
        user.setSocialLogin("original");
        user.setUserStatus(User.UserStatus.USER_EXIST);
//        user.setAbout("안녕하세요");

        return user;
    }

    default UserResponseDto userToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setNickname(user.getNickname());
        userResponseDto.setProfileImage(user.getProfileImage());
//        userResponseDto.setAbout(user.getAbout());
        userResponseDto.setUserRole(user.getUserRole());
        userResponseDto.setSocialLogin(user.getSocialLogin());
        userResponseDto.setUserStatus(user.getUserStatus());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setZipCode(user.getZipCode());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setRestCash(user.getRestCash());

        return userResponseDto;
    }

    default User userPatchDtoToUser(UserService userService, UserPatchDto userPatchDto) {

        long userId = userService.getLoginUser().getUserId();// 토큰에 해당하는 userId 값 가져오기
        User user = new User();
        user.setUserId(userId);
        user.setNickname(userPatchDto.getNickname());
        user.setPassword(userPatchDto.getPassword());
        user.setProfileImage(userPatchDto.getProfileImage());
//        user.setAbout(userPatchDto.getAbout());
        user.setAddress(userPatchDto.getAddress());
        user.setZipCode(userPatchDto.getZipCode());
        user.setPhone(userPatchDto.getPhone());
        user.setUsername(userPatchDto.getUsername());

        return user;
    }

}
