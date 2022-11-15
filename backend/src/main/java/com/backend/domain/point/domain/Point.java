package com.backend.domain.point.domain;

import com.backend.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@SuperBuilder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long cash; // 잔액



}
