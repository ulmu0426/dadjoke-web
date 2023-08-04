package com.ulmu.dadjoketest.controller;

import com.ulmu.dadjoketest.domain.UserCreateForm;
import com.ulmu.dadjoketest.dto.UserDto;
import com.ulmu.dadjoketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Boolean> createUser(@RequestBody final UserCreateForm userCreateForm) throws Exception {
        //회원가입 요청 응답
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            throw new Exception("두 비밀번호가 일치하지 않습니다.");
        }
        //유저정보를 프론트에 줄 필요가 없어서 회원가입 성공 유무만 응답
        Boolean userCreated = userService.createUser(userCreateForm);
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login(@RequestBody final UserDto userLoginDto){
        //로그인 요청
        boolean isValidUser = userService.passwordCheck(userLoginDto.getUserId(), userLoginDto.getPassword());
        if(isValidUser){
            return "joke";
        }
        return "login";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") final Long userId){
        //회원탈퇴 요청
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
