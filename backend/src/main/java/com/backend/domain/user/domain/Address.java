package com.backend.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String address;

//    @Column(nullable = false)
//    private String detailsAddress;

    @Builder
    public Address(String zipCode, String address) {
        this.zipCode = zipCode;
        this.address = address;
    }

}
