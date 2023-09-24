package com.dzvonik.cloud.service;

import com.dzvonik.cloud.model.Role;
import com.dzvonik.cloud.model.User;
import com.dzvonik.cloud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void save(String username, String password) {
        Role role = roleService.findByName("ROLE_USER");

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .enabled(true)
                .roles(Arrays.asList(role))
                .build();

        userRepository.save(user);
    }

}
