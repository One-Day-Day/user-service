package com.lovecode.security.entity;

import lombok.Data;


@Data
public class LoginUserDto {

    private String username;
    private String password;
    private Boolean rememberMe;

}
