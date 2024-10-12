package com.authb.api_auth.service;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.City;
import com.authb.api_auth.entity.Country;
import com.authb.api_auth.entity.Gender;
import com.authb.api_auth.entity.IdType;
import com.authb.api_auth.entity.Province;
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

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

        // Crear una instancia de Gender
        Gender gender = new Gender(1L, "Masculino");

        // Crear una instancia de Country
        Country country = new Country(1L, "Colombia", new HashSet<>());

        // Crear una instancia de Province con la instancia de Country
        Province province = new Province(1L, "Altantico", country, new HashSet<>());

        // Crear una instancia de City con la instancia de Province
        City city = new City(1L, "Barranquilla", province);

        IdType idType = new IdType(1L, "Cédula de ciudadanía");
        Role role = new Role(1L, "Usuario", "Usuario básico", new HashSet<>());


        User mockUser = new User();

        mockUser.setId(1L);
        mockUser.setIdentificationNumber("12345678");
        mockUser.setFirstName("Pedro");
        mockUser.setLastName("Sanchez");
        mockUser.setEmail("PeSa@gmail.com");
        mockUser.setGender(gender); // Asignar la instancia de Gender
        mockUser.setIdType(idType);
        mockUser.setCity(city); // Asignar la instancia de City
        mockUser.setRole(role);
        mockUser.setBirthDate("20 de Octubre de 1990");
        mockUser.setPhoneNumber("1234567890");
        mockUser.setPassword("contraseña");
        mockUser.setAvatarUrl("http://ejemplo.com/avatar.jpg");
        mockUser.setAddress("calle 45 # 80 - 29");

        // Convertir a UserDto
        UserDto userDto = UserService.toUserDto(mockUser);

        // Verificar que los valores sean los esperados
        assertEquals(1L, userDto.getId());
        assertEquals(1, userDto.getIdType());
        assertEquals(1, userDto.getCity());
        assertEquals(1, userDto.getGender());
        assertEquals(1, userDto.getRole());
        assertEquals("12345678", userDto.getIdentificationNumber());
        assertEquals("Pedro", userDto.getFirstName());
        assertEquals("Sanchez", userDto.getLastName());
        assertEquals("PeSa@gmail.com", userDto.getEmail());
        assertEquals("contraseña", userDto.getPassword());
        assertEquals("http://ejemplo.com/avatar.jpg", userDto.getAvatarUrl());
        assertEquals("calle 45 # 80 - 29", userDto.getAddress());
        assertEquals("20 de Octubre de 1990", userDto.getBirthDate());
        assertEquals("1234567890", userDto.getPhoneNumber());

    }


    @Test
    void testToUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentificationNumber("12345678");
        userDto.setFirstName("Pedro");
        userDto.setLastName("Sanchez");
        userDto.setIdType(1);
        userDto.setCity(1);
        userDto.setGender(1);
        userDto.setRole(1);
        userDto.setBirthDate("20 de Octubre de 1990");
        userDto.setPhoneNumber("1234567890");
        userDto.setEmail("PeSa@gmail.com");
        userDto.setPassword("contraseña");
        userDto.setAvatarUrl("http://ejemplo.com/avatar.jpg");
        userDto.setAddress("calle 45 # 80 - 29");

        when(idTypeRepository.findById(1L)).thenReturn(Optional.of(new IdType(1L, "Cédula de ciudadanía")));
        when(cityRepository.findById(1L)).thenReturn(Optional.of(new City(1L, "Barranquilla", new Province())));
        when(genderRepository.findById(1L)).thenReturn(Optional.of(new Gender(1L, "Masculino")));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(new Role(1L, "Usuario", "Usuario básico", new HashSet<>())));
        when(passwordEncoder.encode("contraseña")).thenReturn("contraseñaEncoded");

        User user = userService.toUser(userDto);

        assertEquals("12345678", user.getIdentificationNumber());
        assertEquals("Pedro", user.getFirstName());
        assertEquals("Sanchez", user.getLastName());
        assertEquals("contraseñaEncoded", user.getPassword());
        assertEquals("20 de Octubre de 1990", user.getBirthDate());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals("PeSa@gmail.com", user.getEmail());
        assertEquals("http://ejemplo.com/avatar.jpg", user.getAvatarUrl());
        assertEquals("calle 45 # 80 - 29", user.getAddress());
        assertNotNull(user.getIdType());
        assertNotNull(user.getCity());
        assertNotNull(user.getGender());
        assertNotNull(user.getRole());
    }



    @Test
    void testSignUp() {
        
        // Simulaciones para objetos dependientes
        when(idTypeRepository.findById(1L)).thenReturn(Optional.of(new IdType(1L, "Cédula de ciudadanía")));
        when(cityRepository.findById(1L)).thenReturn(Optional.of(new City(1L, "Barranquilla", new Province())));
        when(genderRepository.findById(1L)).thenReturn(Optional.of(new Gender(1L, "Masculino")));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(new Role(1L, "Usuario", "Usuario básico", new HashSet<>())));
        when(passwordEncoder.encode("contraseña")).thenReturn("contraseñaEncoded");

        // Creación de un UserDto que simule el ingreso del usuario
        UserDto userDto = new UserDto();
        userDto.setFirstName("Juan");
        userDto.setLastName("Pérez");
        userDto.setEmail("juan.perez@example.com");
        userDto.setPassword("contraseña");
        userDto.setIdType(1); // ID en lugar de instancia de IdType
        userDto.setCity(1); // ID en lugar de instancia de City
        userDto.setGender(1); // ID en lugar de instancia de Gender
        userDto.setRole(1); // ID en lugar de instancia de Role
        
        // Simulación de la acción del repositorio que guarda el usuario
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L); // Simula que el ID se asigna al guardar
            return user;
        });

        // Llamada al método de SignUp
        User result = userService.SignUp(userDto);

        // Verificación del resultado esperado
        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
        assertEquals("juan.perez@example.com", result.getEmail());
        assertEquals(1L, result.getId()); // Verifica que el ID se haya asignado correctamente

        // Verificación de que se haya guardado correctamente
        verify(userRepository, times(1)).save(any(User.class));
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
        mockUser.setEmail("test@ejemplo.com");

        when(userRepository.findFirstByEmail("test@ejemplo.com")).thenReturn(Optional.of(mockUser));

        User result = userService.findByEmail("test@ejemplo.com");

        assertEquals("test@ejemplo.com", result.getEmail());
        verify(userRepository, times(1)).findFirstByEmail("test@ejemplo.com");
    }


    @Test
    void testLoadUserByUsername_Success() {
        User mockUser = new User();
        mockUser.setEmail("test@ejemplo.com");
        mockUser.setPassword("contraseñaEncoded");

        when(userRepository.findFirstByEmail("test@ejemplo.com")).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userService.loadUserByUsername("test@ejemplo.com");

        assertEquals("test@ejemplo.com", userDetails.getUsername());
        assertEquals("contraseñaEncoded", userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findFirstByEmail("noexistente@ejemplo.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("noexistente@ejemplo.com");
        });
    }

    @Test
    void testUpdateUser() {

        // Simular datos originales del usuario
        User originalUser = new User();

        originalUser.setId(1L);
        originalUser.setEmail("original@ejemplo.com");
        originalUser.setIdentificationNumber("87654321");
        originalUser.setFirstName("Camilo");
        originalUser.setLastName("Molina");
        originalUser.setBirthDate("10 de enero de 2000");
        originalUser.setPhoneNumber("3004445555");
        originalUser.setPassword("contraseña");
        originalUser.setAvatarUrl("http://ejemplo.com/avatar.jpg");
        originalUser.setAddress("calle 01 # 02 - 03");


        // Supongamos que el usuario ha cambiado su email y nombre
        User updatedUserData = new User();
        updatedUserData.setId(1L);
        updatedUserData.setEmail("updated@ejemplo.com");
        updatedUserData.setFirstName("Carlos");

        // Simular el comportamiento del repositorio:
       
        // Cuando se llama a save(), devuelve el usuario con los nuevos datos
        when(userRepository.save(any(User.class))).thenReturn(updatedUserData);

        // Llamar al método de updateUser del servicio, que debería actualizar el usuario
        User result = userService.updateUser(updatedUserData);

        // Verificar que los datos devueltos por updateUser sean los esperados
        assertEquals("updated@ejemplo.com", result.getEmail());
        assertEquals("Carlos", result.getFirstName());
        
        // Verificar que el método save del repositorio se haya llamado exactamente una vez con el objeto actualizado
        verify(userRepository, times(1)).save(updatedUserData);

    }

}
