package com.ulmu.dadjoketest.service;

import com.ulmu.dadjoketest.domain.User;
import com.ulmu.dadjoketest.domain.UserCreateForm;
import com.ulmu.dadjoketest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserCreateForm userCreateForm;

    public Boolean createUser(UserCreateForm userCreateForm){
        User newUser = new User();
        newUser.setName(userCreateForm.getUserName());
        newUser.setEmail(userCreateForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(userCreateForm.getPassword2()));
        this.userRepository.save(newUser);
        Optional<User> savedUser = this.userRepository.findByName(newUser.getName());

        if (savedUser.isEmpty()){
            return false;
        }else {
            return true;
        }

    }

    public boolean passwordCheck(Long userId, String password){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new NoSuchElementException("해당 유저가 존재하지 않습니다.");
        }
        return passwordEncoder.matches(password, user.get().getPassword());
    }

}
