package com.backend.domain.point.domain;

import com.backend.domain.user.domain.User;
import com.backend.global.audit.Auditable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private int cash;

    private PointType pointType;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;


    }
