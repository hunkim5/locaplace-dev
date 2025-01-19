package com.locaplace.api.user.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private String email;
    private String password;
}