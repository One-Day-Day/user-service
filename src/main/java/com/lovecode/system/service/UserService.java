package com.lovecode.system.service;

import com.lovecode.system.entity.RegisterDto;
import com.lovecode.system.entity.User;
import com.lovecode.system.exception.ConflictException;
import com.lovecode.system.exception.NotFoundException;
import com.lovecode.system.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUser(RegisterDto registerDto) {
        Optional<User> optionalUser = userRepository.findByUsername(registerDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new ConflictException("User name already exist! Please choose another user name.");
        }
        User user = new User();

        user.from(registerDto, bCryptPasswordEncoder);

        userRepository.save(user);
    }

    public User findUserByUserName(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new NotFoundException("No user found with username " + name));
    }

    public void deleteUserByUserName(String name) {
        userRepository.deleteByUsername(name);
    }


    public Page<User> getAllUser(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    public void updateRoleByUsername(String username, String role) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(username));
        user.setRole(role);
        userRepository.save(user);
    }
}
