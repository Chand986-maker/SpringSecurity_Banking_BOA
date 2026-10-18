package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.service;


import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.RegisterUserRequest;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.UserResponse;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.Users;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.repository.UserDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private  final UserDetailsRepository userDetailsRepository;

    public UserService(PasswordEncoder passwordEncoder, UserDetailsRepository userDetailsRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        // TODO check of user is already present
        if (userDetailsRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw new RuntimeException("User is already Exists : ");
        }
        // TODO encode password in request

        Users users = new Users();
        users.setUsername(registerUserRequest.getUsername());
        users.setRole(registerUserRequest.getRole());
        users.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        // TODO save user
        Users saveedUser = userDetailsRepository.save(users);
        return new UserResponse(saveedUser.getId(), saveedUser.getUsername() , saveedUser.getRole().name());
    }
}
