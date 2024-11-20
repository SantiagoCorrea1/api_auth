package com.authb.api_auth;

import com.authb.api_auth.controller.UserResolver;
import com.authb.api_auth.dto.AuthenticationRequest;
import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.repository.RoleRepository;
import com.authb.api_auth.repository.UserRepository;
import com.authb.api_auth.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApiAuthApplicationTests {

	@Autowired
	UserResolver userResolver;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	private User user;

	@Test
	void successfulSingUp(){
		UserDto userDto = new UserDto( null,1,1,1,1,"1","santiago", "correa"
				, "2000-05-07", "a", "a", "a", "a", "a");
		user = userResolver.signUp(userDto).getUser();
		assertEquals(userDto.getEmail(), user.getEmail());

		userRepository.delete(user);
	}
	@AfterEach
	void cleanUp(){
		if (user != null) {
			userRepository.delete(user);
		}
	}

	@Test
	void successfulSignIn(){
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("prueba1@udea.edu.co", "123");
		assertNotNull(userResolver.signIn(authenticationRequest));
	}

//	@Test
//	void adminRol(){
//		assertEquals(roleRepository.findById(Long.parseLong("3")).get().getId(),
//				userResolver.modifyRole("prueba1@udea.edu.co", Long.parseLong("3")).getRole().getId());
//	}

}
