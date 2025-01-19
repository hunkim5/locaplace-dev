package com.locaplace.api.common.entity.user;

import com.locaplace.api.common.enums.Gender;
import com.locaplace.api.common.enums.Yn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNid;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private Yn needPasswordChangeYn = Yn.N;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private Yn verifiedYn = Yn.N;
    private String name;
    private String birthDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private Gender gender;
    private String phoneNumber;
    @Column(nullable = false)
    private String nickname;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private Yn marketingYn = Yn.N;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private Yn locationYn = Yn.N;

    public void verify() {
        this.verifiedYn = Yn.Y;
    }
}
