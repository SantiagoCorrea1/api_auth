package com.authb.api_auth.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private Integer idType;
    private Integer city;
    private Integer gender;
    private Integer role;
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
