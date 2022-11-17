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
        user.setUserName(userPostDto.getUsername());
        user.setPassword(userPostDto.getPassword());
        user.setUserRole("ROLE_USER");
        user.setProfileImage(userPostDto.getProfileImage());
        user.setSocialLogin("original");

        return user;
    }

    default UserResponseDto userToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUsername(user.getUserName());
        userResponseDto.setProfileImage(user.getProfileImage());
        userResponseDto.setUserStatus(user.getUserStatus());
        userResponseDto.setUserRole(user.getUserRole());
        userResponseDto.setSocialLogin(user.getSocialLogin());

        return userResponseDto;
    }

    default User userPatchDtoToUser(UserService userService, UserPatchDto userPatchDto){

        long userId = userService.getLoginUser().getUserId();// 토큰에 해당하는 userId 값 가져오기
        User user = new User();
        user.setUserId(userId);
        user.setEmail(userPatchDto.getEmail());
        user.setProfileImage(userPatchDto.getProfileImage());
        user.setUserName(userPatchDto.getNickname());
        user.setUserStatus(userPatchDto.getUserStatus());
        user.setUserRole(userPatchDto.getUserRole());

        return user;
    }

}
