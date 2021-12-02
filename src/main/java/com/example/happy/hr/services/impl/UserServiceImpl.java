package com.example.happy.hr.services.impl;

import com.example.happy.hr.json.dto.UserDto;
import com.example.happy.hr.json.mapper.UserMapper;
import com.example.happy.hr.repositories.UserRepository;
import com.example.happy.hr.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto getFirstUser() {
        return userMapper.toUserDto(userRepository.findById(1).orElseThrow());
    }
}
