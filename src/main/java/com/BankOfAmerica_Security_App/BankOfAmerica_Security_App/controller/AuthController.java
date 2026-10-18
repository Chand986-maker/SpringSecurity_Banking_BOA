package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.controller;

import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity.AuthRequest;
import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

   /* @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;*/
   private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthController(
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername()
                    , authRequest.getPassword()));

// TODO create JWT token
            return  jwtUtil.generateToken(authRequest.getUsername());
        }catch (Exception exception) {
            throw exception;
        }
    }

}
