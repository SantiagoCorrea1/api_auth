package com.authb.api_auth.service;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.interfaces.UserInterface;
import com.authb.api_auth.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserInterface, UserDetailsService {

    private static GenderRepository genderRepository;
    private static IdTypeRepository idTypeRepository;
    private static CityRepository cityRepository;
    private static RoleRepository roleRepository;
    private static UserRepository userRepository;

    private static PasswordEncoder passwordEncoder;


    public UserService(GenderRepository genderRepository, IdTypeRepository idTypeRepository, CityRepository cityRepository
            , RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        UserService.genderRepository = genderRepository;
        UserService.idTypeRepository = idTypeRepository;
        UserService.cityRepository = cityRepository;
        UserService.roleRepository = roleRepository;
        UserService.userRepository = userRepository;
        UserService.passwordEncoder = passwordEncoder;
    }


    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                Math.toIntExact(user.getIdType().getId()),
                Math.toIntExact(user.getCity().getId()),
                Math.toIntExact(user.getGender().getId()),
                Math.toIntExact(user.getRole().getId()),
                user.getIdentificationNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getAvatarUrl(),
                user.getAddress()
        );
    }
    public User toUser(UserDto userDto) {
        System.out.println(userDto.toString());
        return new User(
                userDto.getId(),
                idTypeRepository.findById(Long.valueOf(userDto.getIdType())).orElse(null) ,
                cityRepository.findById(Long.valueOf(userDto.getCity())).orElse(null) ,
                genderRepository.findById(Long.valueOf(userDto.getGender())).orElse(null),
                roleRepository.findById(Long.valueOf(userDto.getRole())).orElse(null),
                userDto.getIdentificationNumber(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getPhoneNumber(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getAvatarUrl(),
                userDto.getAddress()
        );
    }
    @Override
    public User SignUp(UserDto userDto) {
        return userRepository.save(toUser(userDto));
    }

    public User findById(Long id){
        return (userRepository.findById(id).orElse(null));
    }

    public User findByEmail(String email) {
        return userRepository.findFirstByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email).orElse(null);
        if (user == null) throw new UsernameNotFoundException("email not found", null);
        return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
