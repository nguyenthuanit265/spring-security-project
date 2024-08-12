package com.security.config;


import com.security.model.entity.Role;
import com.security.model.entity.User;
import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(value = "customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsServiceImpl(
            UserRepository userRepository,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        org.springframework.security.core.userdetails.User.UserBuilder userByEmailBuilder = org.springframework.security.core.userdetails.User.withUsername(user.get().getEmail())
                .password(user.get().getPassword());
//        if (!CollectionUtils.isEmpty(user.get().getRoles())) {
//            userByEmailBuilder.authorities(getAuthorities(user.get().getRoles()));
//        }
        return userByEmailBuilder.build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode().toUpperCase()))
                .collect(Collectors.toList());
    }
}
