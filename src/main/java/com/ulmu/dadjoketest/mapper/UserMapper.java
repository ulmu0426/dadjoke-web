package com.ulmu.dadjoketest.mapper;

import com.ulmu.dadjoketest.domain.User;
import com.ulmu.dadjoketest.dto.UserDto;

public class UserMapper {

    public static UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setLoginDate(user.getLoginDate());

        return userDto;
    }

    public static User convertToModel(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setLoginDate(userDto.getLoginDate());

        return user;
    }
}
