package com.backend.domain.point.domain;

import com.backend.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class PointHistory  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointHistoryId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int cash;

    private PointType pointType;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private Long restCash;


    }
