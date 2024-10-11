package com.authb.api_auth.service;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.City;
import com.authb.api_auth.entity.Gender;
import com.authb.api_auth.entity.IdType;
import com.authb.api_auth.entity.User;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private GenderRepository genderRepository;
    @Mock
    private IdTypeRepository idTypeRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(genderRepository, idTypeRepository, cityRepository, roleRepository, userRepository, passwordEncoder);
    }


    @Test
    void testToUserDto() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setIdentificationNumber("12345678");
        mockUser.setFirstName("John");
        mockUser.setLastName("Doe");
        mockUser.setEmail("test@example.com");
        // Configura otros campos si es necesario...

        UserDto userDto = UserService.toUserDto(mockUser);

        assertEquals(1L, userDto.getId());
        assertEquals("12345678", userDto.getIdentificationNumber());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("test@example.com", userDto.getEmail());
    }


    @Test
    void testToUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentificationNumber("12345678");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPassword("password");
        // Configura otros campos si es necesario...

        when(idTypeRepository.findById(anyLong())).thenReturn(Optional.of(new IdType()));
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
        when(genderRepository.findById(anyLong())).thenReturn(Optional.of(new Gender()));
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        User user = userService.toUser(userDto);

        assertEquals("12345678", user.getIdentificationNumber());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("encodedPassword", user.getPassword());
        // Verifica otros campos...
    }



    @Test
    void testSignUp() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        User result = userService.SignUp(userDto);

        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("password");
    }


    @Test
    void testFindById() {
        User mockUser = new User();
        mockUser.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.findById(1L);

        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void testFindByEmail() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        when(userRepository.findFirstByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        User result = userService.findByEmail("test@example.com");

        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).findFirstByEmail("test@example.com");
    }


    @Test
    void testLoadUserByUsername_Success() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("encodedPassword");

        when(userRepository.findFirstByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userService.loadUserByUsername("test@example.com");

        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findFirstByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent@example.com");
        });
    }

    @Test
    void testUpdateUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("updated@example.com");

        when(userRepository.save(mockUser)).thenReturn(mockUser);

        User updatedUser = userService.updateUser(mockUser);

        assertEquals("updated@example.com", updatedUser.getEmail());
        verify(userRepository, times(1)).save(mockUser);
    }



}
