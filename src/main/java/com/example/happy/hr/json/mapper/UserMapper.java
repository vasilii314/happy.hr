package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.User;
import com.example.happy.hr.json.dto.UserDto;

public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto dto);
}
