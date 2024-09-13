package com.authb.api_auth.controller;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.service.UserService;
import graphql.annotations.annotationTypes.GraphQLTypeResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserResolver implements GraphQLMutationResolver {
    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userService.findById(id);
    }

    @MutationMapping
    public User signUp(@Argument("input") UserDto userDto) {

        return userService.SignUp(userDto);
    }
}
