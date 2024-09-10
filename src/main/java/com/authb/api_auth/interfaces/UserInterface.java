package com.authb.api_auth.interfaces;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.repository.UserRepository;

public interface UserInterface {

    User SignUp(UserDto userDto);

}
