package com.locaplace.api.common.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "jwt_token")
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jwtNid;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nid", nullable = false)
    @Getter
    private User user;
    @Column(nullable = false, unique = true)
    private String tokenHash;
    @Column(nullable = false)
    private LocalDateTime expireDtm;
}