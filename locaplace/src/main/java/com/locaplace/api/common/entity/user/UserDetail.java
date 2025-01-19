package com.locaplace.api.common.entity.user;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_detail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_nid")
    private Long userDetailNid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nid")
    private User user;
    @Column(nullable = false)
    private String userType;
    @Setter
    private String introduction;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private Yn foreignYn = Yn.N;
    private String residentNumber;
    private String passportNumber;
    private String authenticationStep;
    private String profileImg;
}
