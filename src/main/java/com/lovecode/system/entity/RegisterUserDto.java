package com.lovecode.system.entity;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private String email;
    private String role;

}