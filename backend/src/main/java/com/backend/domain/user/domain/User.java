package com.backend.domain.user.domain;


import com.backend.domain.order.domain.Order;
import com.backend.domain.user.dto.UserPatchDto;
import com.backend.global.audit.Auditable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String about;

    @Column(nullable = true)
    private String userRole;

    @Column(nullable = false)
    private String socialLogin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private UserStatus userStatus = UserStatus.USER_EXIST;

    @Column
    private String zipCode;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String username;

    @Column
    private int restCash;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public User(String email,
                String password,
                String nickname,
                String profileImage,
                String about,
                String userRole,
                String socialLogin,
                String zipCode,
                String address,
                int restCash) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.about = about;
        this.userRole = userRole;
        this.socialLogin = socialLogin;
        this.zipCode = zipCode;
        this.address = address;
        this.restCash = restCash;
    }

    public void patch(UserPatchDto userPatchDto, String password) {
        this.nickname = userPatchDto.getNickname();
        this.password = password;
        this.profileImage = userPatchDto.getProfileImage();
        this.about = userPatchDto.getAbout();
        this.address = userPatchDto.getAddress();
        this.zipCode = userPatchDto.getZipCode();
        this.phone = userPatchDto.getPhone();
        this.username = userPatchDto.getUsername();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public enum UserStatus {
        USER_EXIST("존재하는 유저"),
        USER_NOT_EXIST("존재하지 않는 유저");

        @Getter
        private String status;

        UserStatus(String status) {
            this.status = status;
        }
    }
}
