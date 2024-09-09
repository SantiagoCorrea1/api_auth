package com.authb.api_auth.mapper;

import com.authb.api_auth.dto.CountryDto;
import com.authb.api_auth.entity.Country;
import com.authb.api_auth.entity.Province;
import com.authb.api_auth.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CountryMapper {

    private static ProvinceRepository provinceRepository;

    public CountryMapper(ProvinceRepository provinceRepository) {
        CountryMapper.provinceRepository = provinceRepository;
    }

    public static CountryDto toCountryDtoCountryDto (Country country){
        Set<String> provincesNames = new HashSet<>();
        for(Province province: country.getProvinces()){
            provincesNames.add(province.getName());
        }
        return new CountryDto(
                country.getId(),
                country.getName(),
                provincesNames
        );
    }

    public static Country toCountry(CountryDto countryDto){
        Set<Province>provinces = new HashSet<>();
        for (String provinceName : countryDto.getProvinceNames()){
            provinces.add(provinceRepository.findByName(provinceName).orElse(null));
        }
        return new Country(
                countryDto.getId(),
                countryDto.getName(),
                provinces
        );
    }
}
