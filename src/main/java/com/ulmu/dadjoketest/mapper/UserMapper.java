package com.ulmu.dadjoketest.mapper;

import com.ulmu.dadjoketest.domain.SiteUser;
import com.ulmu.dadjoketest.dto.UserDto;

public class UserMapper {

    public static UserDto convertToDto(SiteUser user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setLoginDate(user.getLoginDate());

        return userDto;
    }

    public static SiteUser convertToModel(UserDto userDto){
        SiteUser user = new SiteUser();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setLoginDate(userDto.getLoginDate());

        return user;
    }

}
