package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.controller;


import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.RegisterUserRequest;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.Role;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.UserResponse;

import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUSer(@RequestBody RegisterUserRequest registerUserRequest) {
        registerUserRequest.setRole(Role.USER);
        UserResponse userResponse =  userService.registerUser(registerUserRequest);

        return ResponseEntity.ok(userResponse);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/create")
    public ResponseEntity<UserResponse> registerByAdmin(@RequestBody RegisterUserRequest registerUserRequest)  {
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }
}
