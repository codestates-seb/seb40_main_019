package com.backend.domain.community.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CommunityCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityCategoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String categoryRefCode;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "community_category_id")
    private List<Community> communities = new ArrayList<>();

}
