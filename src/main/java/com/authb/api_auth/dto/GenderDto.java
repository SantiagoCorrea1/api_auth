package com.authb.api_auth.dto;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GenderDto {

    private Long id;

    private String gender;

    public GenderDto() {
    }

    public GenderDto(Long id, String gender) {
        this.id = id;
        this.gender = gender;
    }
}
