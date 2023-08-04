package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.User;
import com.ulmu.dadjoketest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void createUser() {
        User newUser = new User();
        newUser.setName("테스트");
        newUser.setEmail("test@naver.com");
        newUser.setPassword(passwordEncoder.encode("testPass"));
        this.userRepository.save(newUser);

        Optional<User> createdUser = userRepository.findByName("테스트");
        assertTrue(!createdUser.isEmpty());
        System.out.println(createdUser.get().getUserId());
        System.out.println(createdUser.get().getName());
        System.out.println(createdUser.get().getEmail());
        System.out.println(createdUser.get().getPassword());
        System.out.println(createdUser.get().getCreatedAt());
    }
}