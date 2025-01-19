package com.locaplace.api.common.entity.verification;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locaplace.api.common.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nice_token_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NiceTokenInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long niceTokenNid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nid")
    private User user;
    private String siteCode;
    private String tokenVersionId;
    private String tokenVal;
    private Long periodSecond;
    private String reqNo;
    @JsonIgnore
    private LocalDateTime createDtm;
    private String plainKey;
    private String plainIv;
    private String hmacKey;

    @Builder
    public NiceTokenInfo(User user, String siteCode, String tokenVersionId, String tokenVal, Long periodSecond, String reqNo, LocalDateTime createDtm) {
        this.user = user;
        this.siteCode = siteCode;
        this.tokenVersionId = tokenVersionId;
        this.tokenVal = tokenVal;
        this.periodSecond = periodSecond;
        this.reqNo = reqNo;
        this.createDtm = createDtm;
    }

}
