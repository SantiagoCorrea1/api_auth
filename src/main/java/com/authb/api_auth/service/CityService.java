package com.authb.api_auth.service;

import com.authb.api_auth.dto.CityDto;
import com.authb.api_auth.entity.City;
import com.authb.api_auth.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private static ProvinceRepository provinceRepository;

    public CityService(ProvinceRepository provinceRepository) {
        CityService.provinceRepository = provinceRepository;
    }

    public CityDto toCityDto(City city) {
        return new CityDto(
                city.getId(),
                city.getName(),
                city.getProvince().getName()
        );
    }

    public City toCity(CityDto cityDto) {
        return new City(
                cityDto.getId(),
                cityDto.getCityName(),
                provinceRepository.findByName(cityDto.getProvinceName()).orElse(null)
        );
    }
}
