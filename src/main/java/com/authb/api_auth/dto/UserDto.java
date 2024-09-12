package com.authb.api_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private Long idTypeName;
    private Long cityName;
    private Long genderName;
    private Long roleName;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String password;
    private String avatarUrl;
    private String address;
}
