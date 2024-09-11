package com.authb.api_auth.controller;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.service.UserService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserResolver {
    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User userById(Long id) {
        return userService.findById(id);
    }

    @MutationMapping
    public User signUp(UserDto userDto) {
        return userService.SignUp(userDto);
    }
}
