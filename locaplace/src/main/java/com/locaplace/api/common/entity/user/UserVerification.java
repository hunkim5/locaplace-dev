package com.locaplace.api.common.entity.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.locaplace.api.common.enums.Yn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_verification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userVerificationNid;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nid")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_nid")
    private UserDetail userDetail;
    @Column(nullable = false)
    private String emailVerificationToken;
    private LocalDateTime emailVerificationExpiredDtm;
    @Setter
    private String passwordChangeToken;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private Yn passwordChangeVerificationYn = Yn.N;
    private LocalDateTime passwordVerificationExpiredDtm;

    @Builder
    public UserVerification(User user, UserDetail userDetail) {
        this.user = user;
        this.userDetail = userDetail;
        this.emailVerificationToken = UUID.randomUUID().toString();
        this.emailVerificationExpiredDtm = LocalDateTime.now().plusMinutes(30);
        this.passwordChangeVerificationYn = Yn.N;
    }

}
