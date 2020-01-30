package com.lovecode.security.service;

import com.lovecode.security.entity.JwtUserDto;
import com.lovecode.system.entity.User;
import com.lovecode.system.exception.NotFoundException;
import com.lovecode.system.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws NotFoundException {
        User user = userService.findUserByUserName(name);
        return new JwtUserDto(user);
    }

}
