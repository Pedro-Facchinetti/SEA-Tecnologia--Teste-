package com.pedrofacchinetti.TesteBackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pedrofacchinetti.TesteBackEnd.model.User;
import com.pedrofacchinetti.TesteBackEnd.service.UserService;
import com.pedrofacchinetti.TesteBackEnd.util.JwtProvider;
import com.pedrofacchinetti.TesteBackEnd.dto.LoginRequest;
import com.pedrofacchinetti.TesteBackEnd.dto.LoginResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autenticação")
@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @ApiOperation(value = "Autentica um usuário e retorna um token JWT")
    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
            String token = jwtProvider.generateToken(user.getUsername(), user.getRole().toString());
            return ResponseEntity.ok(new LoginResponse(user.getUsername(), user.getRole().toString(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}