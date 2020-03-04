package com.sadlestudios.FalloutFigureApi.controllers;

import com.sadlestudios.FalloutFigureApi.models.User;
import com.sadlestudios.FalloutFigureApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User Get(@PathVariable("id")UUID id) {
        return userService.getUser(id);
    }
}
