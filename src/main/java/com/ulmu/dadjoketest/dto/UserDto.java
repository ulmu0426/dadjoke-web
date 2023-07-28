package com.ulmu.dadjoketest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private Long userId;

    private String name;

    private String email;

    private String password;

    private Date createdAt;

    private Date loginDate;

}
