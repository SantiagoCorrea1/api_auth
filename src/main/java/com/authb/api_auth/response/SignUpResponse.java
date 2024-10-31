package com.authb.api_auth.response;

import com.authb.api_auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class SignUpResponse {

    private User user;
    private String message;

    public SignUpResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }
}