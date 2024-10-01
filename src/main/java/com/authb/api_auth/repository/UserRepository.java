package com.authb.api_auth.repository;

import com.authb.api_auth.dto.UserDto;
import com.authb.api_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstName(String name);

    User findFirstByEmail(String email);

}
