package com.bootcamp.rule_engine.repository;

import com.bootcamp.rule_engine.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query(value = "SELECT u FROM USER WHERE u.email = :email ", nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
