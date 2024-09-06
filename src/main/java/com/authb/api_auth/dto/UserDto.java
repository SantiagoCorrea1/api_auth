package com.authb.api_auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String idTypeName;
    private String cityName;
    private String genderName;
    private String roleName;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String idTypeName, String cityName, String genderName, String roleName, String identificationNumber, String firstName, String lastName, Date birthDate, String phoneNumber, String email, String password) {
        this.id = id;
        this.idTypeName = idTypeName;
        this.cityName = cityName;
        this.genderName = genderName;
        this.roleName = roleName;
        this.identificationNumber = identificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}
