package com.authb.api_auth.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CountryDto {

    private Long id;
    private String name;

    public CountryDto() {
    }

    public CountryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
