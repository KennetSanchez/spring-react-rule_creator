package com.bootcamp.rule_engine.repository;

import com.bootcamp.rule_engine.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

}
