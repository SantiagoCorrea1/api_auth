package com.authb.api_auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDto {
    private Long id;
    private String provinceName;
    private String countryName;
    private Set<String> cities;

}
