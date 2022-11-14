package com.backend.domain.user.domain;


import com.backend.domain.order.domain.Order;
import com.backend.domain.user.dto.UserPatchDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String about;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Address> addresses = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "user_id")
//    private List<Cart> carts = new ArrayList<>();

    @Builder
    public User(String email, String password, String userName, String profileImage, String about, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.profileImage = profileImage;
        this.about = about;
        this.userRole = userRole;
    }

    public void patch(UserPatchDto userPatchDto, String password) {
        this.userName = userPatchDto.getUsername();
        this.password = password;
        this.profileImage = userPatchDto.getProfileImage();
        this.about = userPatchDto.getAbout();
        this.addresses = userPatchDto.getAddress();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
