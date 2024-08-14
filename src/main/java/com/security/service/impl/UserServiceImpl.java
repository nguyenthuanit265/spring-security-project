package com.security.service.impl;


import com.security.model.dto.SignUpRequest;
import com.security.model.dto.SpringSecurityUserDetailsDto;
import com.security.model.dto.UserDto;
import com.security.model.entity.User;
import com.security.repository.UserRepository;
import com.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        if (ObjectUtils.isEmpty(email)) {
            return Optional.empty();
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return Optional.ofNullable(UserDto.builder().id(user.get().getId()).email(user.get().getEmail()).name(user.get().getName()).build());
        }
        return Optional.empty();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseEntity<?> signUp(SignUpRequest request, HttpServletRequest servletRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>("User is existed", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        String hashPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPassword);
        User saved = userRepository.save(user);
        return new ResponseEntity<>(modelMapper.map(saved, UserDto.class), HttpStatus.CREATED);
    }

    @jakarta.transaction.Transactional
    @Override
    public Optional<SpringSecurityUserDetailsDto> findByUsername(String username) {
        if (ObjectUtils.isEmpty(username)) {
            return Optional.empty();
        }
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return Optional.ofNullable(SpringSecurityUserDetailsDto.builder().id(user.get().getId()).email(user.get().getEmail()).name(user.get().getName()).build());
        }
        return Optional.empty();
    }
}
