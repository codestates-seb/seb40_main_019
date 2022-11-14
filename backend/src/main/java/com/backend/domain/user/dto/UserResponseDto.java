package com.backend.domain.user.dto;

import com.backend.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long reputation;
    private Long userId;
    private String profileImage;
    private String username;
    private String link;

    @Builder
    public UserResponseDto(Long reputation, Long userId, String profileImage, String username, String link) {
        this.reputation = reputation;
        this.userId = userId;
        this.profileImage = profileImage;
        this.username = username;
        this.link = link;
    }

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .profileImage(user.getProfileImage())
                .userId(user.getUserId())
                .username(user.getUserName())
                .link(memberLink(user))
                .build();
    }

    private static String memberLink(User user) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("member/").path(user.getUserId().toString())
                .replaceQuery(null)
                .toUriString();

    }

}
