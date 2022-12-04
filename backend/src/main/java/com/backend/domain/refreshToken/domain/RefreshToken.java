package com.backend.domain.refreshToken.domain;

import com.backend.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends Auditable {
    @Id
    @Column(name = "rt_key")
    // member id 값이 들어감
    private Long key;

    @Column(name = "rt_value", length = 500)
    // refresh token (String)
    private String value;

    @Column(name = "rt_expiration_date")
    private Date expirationDate;

    @Builder
    public RefreshToken(Long key, String value, Date expirationDate) {
        this.key = key;
        this.value = value;
        this.expirationDate = expirationDate;
    }
}
