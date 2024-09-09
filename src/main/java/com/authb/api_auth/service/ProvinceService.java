package com.authb.api_auth.service;

import com.authb.api_auth.dto.ProvinceDto;
import com.authb.api_auth.entity.City;
import com.authb.api_auth.entity.Province;
import com.authb.api_auth.repository.CityRepository;
import com.authb.api_auth.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProvinceService {
    private static CityRepository cityRepository;
    private static CountryRepository countryRepository;

    public ProvinceService(CityRepository cityRepository, CountryRepository countryRepository) {
        ProvinceService.cityRepository = cityRepository;
        ProvinceService.countryRepository = countryRepository;
    }

    public static ProvinceDto toProvinceDto(Province province) {
        Set<String>cityNames = new HashSet<>();
        for(City city : province.getCities()) {
            cityNames.add(city.getName());
        }
        return new ProvinceDto(
                province.getId(),
                province.getName(),
                province.getCountry().getName(),
                cityNames
        );
    }
    public static Province toProvince(ProvinceDto provinceDto){
        Set<City> cities =new HashSet<>();
        for (String cityName : provinceDto.getCities()) {
            cities.add(cityRepository.findByName(cityName).orElse(null));
        }
        return new Province(
                provinceDto.getId(),
                provinceDto.getProvinceName(),
                countryRepository.findByName(provinceDto.getCountryName()).orElse(null),
                cities
        );
    }
}
