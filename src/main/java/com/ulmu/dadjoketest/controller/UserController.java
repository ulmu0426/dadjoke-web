package com.ulmu.dadjoketest.controller;

import com.ulmu.dadjoketest.dto.UserDto;
import com.ulmu.dadjoketest.service.UserCreateForm;
import com.ulmu.dadjoketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserDto> createUser(@RequestBody final UserCreateForm userCreateForm) throws Exception {
        //회원가입 요청 응답
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            throw new Exception("두 비밀번호가 일치하지 않습니다.");
        }

        UserDto newUser = userService.createUser(userCreateForm);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
