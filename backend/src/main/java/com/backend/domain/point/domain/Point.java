package com.backend.domain.point.domain;

import com.backend.domain.user.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Setter
@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int cash; // 잔액


    }
