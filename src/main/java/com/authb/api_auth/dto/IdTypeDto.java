package com.authb.api_auth.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdTypeDto {

    private Long id;

    private String type;

    public IdTypeDto() {
    }

    public IdTypeDto(Long id, String type) {
        this.id = id;
        this.type = type;
    }
}
