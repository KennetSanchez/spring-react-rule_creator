package com.bootcamp.rule_engine.repository;

import com.bootcamp.rule_engine.model.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PermissionRepository extends CrudRepository<Permission, UUID> {

}
