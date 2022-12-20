package com.bootcamp.rule_engine.repository;

import com.bootcamp.rule_engine.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecordRepository extends CrudRepository<Record, UUID> {
}
