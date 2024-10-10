package com.authb.api_auth.controller;

import com.authb.api_auth.dto.AuthenticationRequest;
import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.repository.UserRepository;
import com.authb.api_auth.service.UserService;
<<<<<<< HEAD
=======
import com.authb.api_auth.util.JwtUtil;
>>>>>>> 37edaab9facb931bca032a399bb27f7989a38751
import graphql.kickstart.tools.GraphQLMutationResolver;
import io.jsonwebtoken.io.IOException;
import jakarta.annotation.security.PermitAll;
import org.json.JSONException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserResolver implements GraphQLMutationResolver {
    private final UserService userService;

    final AuthenticationManager authenticationManager;
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserResolver(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userService.findById(id);
    }

    @PermitAll
    @MutationMapping
    public User signUp(@Argument("input") UserDto userDto) {
        return userService.SignUp(userDto);
    }

    @PermitAll
    @MutationMapping
    public void createAuthenticationToken (@Argument("input")  AuthenticationRequest authenticationRequest,
                                           HttpServletResponse response) throws IOException, JSONException, java.io.IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e){
             throw new BadCredentialsException("UserName or password incorrect", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        User user = userRepository.findFirstByEmail(userDetails.getUsername()).orElse(null);

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(),user.getId());

        response.getWriter().write(new JSONObject()
                .put("token",jwt)
                .toString());

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization"+
                " X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);


        }

    @PermitAll
    @QueryMapping
    public User userByEmail(@Argument String email) {
        return userService.findByEmail(email);
    }

    @PermitAll
    @MutationMapping
    public User modifyRole(@Argument String email,
                           @Argument() Long roleId) {

        User user = userService.findByEmail(email);
        Role role = com.authb.api_auth.service.RolService.findById(roleId);
        user.setRole(role);
        return userService.updateUser(user);
    }



}
