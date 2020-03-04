package com.sadlestudios.FalloutFigureApi.controllers;

import com.sadlestudios.FalloutFigureApi.models.LoginCredentials;
import com.sadlestudios.FalloutFigureApi.models.NewUserInfo;
import com.sadlestudios.FalloutFigureApi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("accounts")
public class AccountsController {

    private final AuthenticationService authService;

    @Autowired
    public AccountsController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/CreateUser")
    public UUID CreateUser(@RequestBody NewUserInfo user) {
        return authService.CreateUser(user);
    }

    @PostMapping("/UserLogin")
    public boolean UserLogin(@RequestBody LoginCredentials credentials) {
        return authService.CheckUserPassword(credentials);
    }
}
