package com.example.happy.hr.services.impl;

import com.example.happy.hr.json.dto.UserDto;
import com.example.happy.hr.json.mapper.UserMapper;
import com.example.happy.hr.repositories.UserRepository;
import com.example.happy.hr.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto getFirstUser() {
        log.info("Getting dummy user info");
        return userMapper.toUserDto(userRepository.findById(1).orElseThrow());
    }
}
