package com.authb.api_auth.mapper;

import com.authb.api_auth.dto.GenderDto;
import com.authb.api_auth.entity.Gender;
import org.springframework.stereotype.Service;

@Service
public class GenderMapper {

    public static GenderDto toGenderDto(Gender gender){
        return new GenderDto(
                gender.getId(),
                gender.getName()
        );
    }
    public static Gender toGender(GenderDto genderDto){
        return new Gender(
                genderDto.getId(),
                genderDto.getGender()
        );
    }
}
