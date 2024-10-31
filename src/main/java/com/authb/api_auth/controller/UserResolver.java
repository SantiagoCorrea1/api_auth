package com.authb.api_auth.controller;


import com.authb.api_auth.dto.AuthenticationRequest;
import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.repository.UserRepository;
import com.authb.api_auth.service.UserService;
import com.authb.api_auth.util.JwtUtil;
import com.authb.api_auth.response.SignUpResponse;
import com.authb.api_auth.util.PasswordUtil;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.annotation.security.PermitAll;
import org.json.JSONException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserResolver implements GraphQLMutationResolver {
    private final UserService userService;
    final AuthenticationManager authenticationManager;
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;

    public UserResolver(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordUtil passwordUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @QueryMapping
    public User userById(@Argument Long id) {
        return userService.findById(id);
    }

    @PermitAll
    @MutationMapping
    public SignUpResponse signUp(@Argument("input") UserDto userDto) {

        if (!passwordUtil.isPasswordValid(userDto.getPassword())) {
            // Return a custom error message in case of invalid password
            return new SignUpResponse(null, "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
        }
        if(!passwordUtil.isIdValid(userDto.getIdentificationNumber())){
            return new SignUpResponse(null, "Please insert a valid ID number");
        }
        if(!passwordUtil.isBirthDateValid(userDto.getBirthDate())){
            return new SignUpResponse(null, "Please insert a valid date");
        }
        if(!passwordUtil.isCityValid(userDto.getCity().toString())){
            return new SignUpResponse(null, "Please insert a valid city");
        }
        if(!passwordUtil.isFirstNameValid(userDto.getFirstName())){
            return new SignUpResponse(null, "Please insert a valid name");
        }
        if(!passwordUtil.isEmailValid(userDto.getEmail())){
            return new SignUpResponse(null, "Please insert a valid Email");
        }
        if(!passwordUtil.isGenderValid(userDto.getGender().toString())){
            return new SignUpResponse(null, "Please select an option");
        }
        if (!passwordUtil.isIdTypeValid(userDto.getIdType().toString())){
            return new SignUpResponse(null, "Please select a valid ID type");
        }
        if (!passwordUtil.isLastNameValid(userDto.getLastName())){
            return new SignUpResponse(null, "Please insert a valid lastname");
        }
        if (!passwordUtil.isPhoneNumberValid(userDto.getPhoneNumber())){
            return new SignUpResponse(null, "Please insert a valid phoneNumber");
        }
        if (userService.findByEmail(userDto.getEmail()) != null){
            return new SignUpResponse(null, "The email is already registered");
        }
        if (userService.findByIdentificationNumber(userDto.getIdentificationNumber()) != null){
            return new SignUpResponse(null, "The ID number is already registered");
        }
        if (userService.findByPhoneNumber(userDto.getPhoneNumber()) != null){
            return new SignUpResponse(null, "The phone number is already registered");
        }
        // Proceed with the sign-up process and return a success response
        User user = userService.SignUp(userDto);
        return new SignUpResponse(user, "The user was created successfully");
    }

    @PermitAll
    @MutationMapping
    public String signIn(@Argument("input") AuthenticationRequest authenticationRequest)
            throws JSONException {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            System.out.println("Autenticación exitosa.");
        } catch (BadCredentialsException e) {
            // Capturar excepciones de credenciales incorrectas
            throw new GraphQLException("Invalid username or password");

        } catch (AuthenticationException e) {
            // Capturar otras excepciones de autenticación
            throw new GraphQLException("Unauthorized");
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        User user = userRepository.findFirstByEmail(userDetails.getUsername()).orElse(null);

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.getId());

        return jwt;
    }

    @PermitAll
    @QueryMapping
    public User userByEmail(@Argument String email) {
        return userService.findByEmail(email);
    }

    @PermitAll
    @MutationMapping
    public User modifyRole(@Argument String email,
                           @Argument Long roleId) {

        User user = userService.findByEmail(email);
        Role role = com.authb.api_auth.service.RolService.findById(roleId);
        user.setRole(role);
        return userService.updateUser(user);
    }



}
