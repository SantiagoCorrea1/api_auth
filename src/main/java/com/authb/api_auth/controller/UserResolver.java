package com.authb.api_auth.controller;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
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
    public User userByEmail(@Argument String email) {
        return userService.findByEmail(email);
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userService.findById(id);
    }

    @MutationMapping
    public User signUp(@Argument("input") UserDto userDto) {
        return userService.SignUp(userDto);
    }

    @MutationMapping
    public User modifyRole(@Argument String email,
                           @Argument() Long roleId) {

        User user = userService.findByEmail(email);
        Role role = com.authb.api_auth.service.RolService.findById(roleId);
        user.setRole(role);
        return userService.UpdateUser(user);
    }
}
