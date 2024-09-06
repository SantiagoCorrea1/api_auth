package com.authb.api_auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {

    private Long id;
    private String city;

    public CityDto() {
    }

    public CityDto(Long id, String city) {
        this.id = id;
        this.city = city;
    }
}
