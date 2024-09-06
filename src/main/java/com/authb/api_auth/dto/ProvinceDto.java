package com.authb.api_auth.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinceDto {

    private Long id;

    private String country;

    private String provinceName;

    public ProvinceDto() {
    }

    public ProvinceDto(Long id, String country, String province) {
        this.id = id;
        this.country = country;
        this.provinceName = province;
    }
}
