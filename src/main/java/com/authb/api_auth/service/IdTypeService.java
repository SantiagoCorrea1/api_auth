package com.authb.api_auth.service;

import com.authb.api_auth.dto.IdTypeDto;
import com.authb.api_auth.entity.IdType;
import org.springframework.stereotype.Service;

@Service
public class IdTypeService {
    public IdTypeDto toIdTypeDto(IdType idType) {
        return new IdTypeDto(idType.getId(), idType.getName());
    }

    public IdType toIdType(IdTypeDto idTypeDto) {
        return new IdType(
                idTypeDto.getId(),
                idTypeDto.getTypeName()
        );
    }
}
