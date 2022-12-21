package com.bootcamp.rule_engine.repository;

import com.bootcamp.rule_engine.model.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository extends CrudRepository<Record, UUID> {
    @Query(value = "SELECT record FROM RECORDS WHERE record.:field :operation :condition ", nativeQuery = true)
    List<Record> filterRecords(String field, String operation, String condition);
}
