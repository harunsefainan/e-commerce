package com.harunsefainan.ecommerce.controller;

import com.harunsefainan.ecommerce.auth.JwtTokenManager;
import com.harunsefainan.ecommerce.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/authenticate")
@AllArgsConstructor
public class AuthController {

    private JwtTokenManager jwtTokenManager;

    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            return ResponseEntity.ok(jwtTokenManager.generateToken(loginRequest.getUsername()));
        }catch (Exception e){
            throw e;
        }
    }
}
