package com.bootcamp.rule_engine.repository;

import com.bootcamp.rule_engine.model.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository extends CrudRepository<Record, UUID> {

    //The first statements are hard-coded in order to reduce the risk of executing malicious commands
    @Query(value = "SELECT record FROM RECORDS WHERE :queryString", nativeQuery = true)
    List<Record> filterRecords(String queryString);

    @Query(value = "SELECT * FROM information_schema.columns  WHERE table_name   = 'RECORDS'", nativeQuery = true)
    List<String> getColumnsNames();
}
