package com.example.happy.hr.controllers;

import com.example.happy.hr.json.dto.UserDto;
import com.example.happy.hr.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(value = "/api/users/auth", produces = "application/json")
    public ResponseEntity<UserDto> getSampleUser() {
        return ResponseEntity.ok(userService.getFirstUser());
    }
}
