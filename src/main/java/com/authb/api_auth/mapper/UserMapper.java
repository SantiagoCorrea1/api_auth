package com.authb.api_auth.mapper;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private static GenderRepository genderRepository;
    private static IdTypeRepository idTypeRepository;
    private static CityRepository cityRepository;
    private static RoleRepository roleRepository;
    public UserMapper(GenderRepository genderRepository, IdTypeRepository idTypeRepository, CityRepository cityRepository, RoleRepository roleRepository) {
        this.genderRepository = genderRepository;
        this.idTypeRepository = idTypeRepository;
        this.cityRepository = cityRepository;
        this.roleRepository = roleRepository;
    }


    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getIdType().getName(),
                user.getCity().getName(),
                user.getGender().getName(),
                user.getRole().getName(),
                user.getIdentificationNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword()
        );
    }
    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                idTypeRepository.findByName(userDto.getIdTypeName()).orElseThrow(null) ,
                cityRepository.findByName(userDto.getCityName()).orElseThrow(null) ,
                genderRepository.findByName(userDto.getGenderName()).orElseThrow(null),
                roleRepository.findByName(userDto.getRoleName()).orElseThrow(null),
                userDto.getIdentificationNumber(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getPhoneNumber(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }
}
