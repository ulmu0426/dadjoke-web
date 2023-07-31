package com.ulmu.dadjoketest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "사용자 이름은 필수항목입니다.")
    private String userName;    //가입자 이름

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;   //패스워드

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;   //패스워드 확인

    @Email
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;   //이메일
}
