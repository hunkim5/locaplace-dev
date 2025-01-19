package com.locaplace.api.user.vo;

import com.locaplace.api.common.enums.Gender;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class UserUpdateVo {
    @Nullable
    String nickname;
    @Nullable
    String name;
    @Nullable
    String birthDate;
    @Nullable
    Gender gender;
    @Nullable
    String PhoneNumber;
    //아래는 UserDetail
    @Nullable
    String introduction;
}
