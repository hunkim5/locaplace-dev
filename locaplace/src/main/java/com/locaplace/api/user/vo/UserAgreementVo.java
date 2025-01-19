package com.locaplace.api.user.vo;

import com.locaplace.api.common.enums.Yn;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class UserAgreementVo {
    @Nullable
    Yn marketingYn;
    @Nullable
    Yn locationYn;
}
