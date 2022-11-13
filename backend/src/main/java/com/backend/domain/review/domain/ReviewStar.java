package com.backend.domain.review.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ReviewStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewStarId;

    @Column(name = "star_score", nullable = false)
    private int star_score;

}
