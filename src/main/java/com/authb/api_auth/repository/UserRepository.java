package com.authb.api_auth.repository;

import com.authb.api_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByFirstName(String name);

    Optional<User> findFirstByEmail(String email);
    Optional<User> findByIdentificationNumber(String id);

    Optional<User> findByPhoneNumber(String number);



}
