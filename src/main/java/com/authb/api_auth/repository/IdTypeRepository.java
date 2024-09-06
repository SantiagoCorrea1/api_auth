package com.authb.api_auth.repository;

import com.authb.api_auth.entity.IdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdTypeRepository extends JpaRepository<IdType, Long> {

    Optional<IdType> findByName(String name);
}
