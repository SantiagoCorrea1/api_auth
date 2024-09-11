package com.authb.api_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String idTypeName;
    private String cityName;
    private String genderName;
    private String roleName;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;
    private String password;
    private String url_avatar;
}
