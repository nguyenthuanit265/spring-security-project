package com.security.controller;

import com.security.config.JwtTokenProvider;
import com.security.model.base.AppResponse;
import com.security.model.dto.AuthRequest;
import com.security.model.dto.AuthResponse;
import com.security.model.dto.SignUpRequest;
import com.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, AuthenticationManager authManager) {
        this.userService = userService;
        this.authManager = authManager;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest req, HttpServletRequest request) {
        return userService.signUp(req, request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request, HttpServletRequest req) {
        // Spring Security use authenticate function -> call functions loadUserByUsername and get username and password -> using PasswordEncoder Bean authenticate user login
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        // Get user login info
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // String jwt = jwtTokenProvider.generateToken(authentication);
        String jwt = jwtTokenProvider.generateAccessToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(jwt);
        return new ResponseEntity<>(AppResponse.buildResponse(HttpStatus.OK, authResponse), HttpStatus.OK);
    }
}
