package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.User;
import com.ulmu.dadjoketest.domain.UserCreateForm;
import com.ulmu.dadjoketest.dto.UserDto;
import com.ulmu.dadjoketest.mapper.UserMapper;
import com.ulmu.dadjoketest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserCreateForm userCreateForm;
    public UserDto createUser(UserCreateForm userCreateForm){

        User newUser = new User();
        newUser.setName(userCreateForm.getUserName());
        newUser.setEmail(userCreateForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(userCreateForm.getPassword2()));
        this.userRepository.save(newUser);

        return UserMapper.convertToDto(newUser);
    }

}
