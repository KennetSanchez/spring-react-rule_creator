package com.bootcamp.rule_engine.service.impl;

import com.bootcamp.rule_engine.model.Record;
import com.bootcamp.rule_engine.repository.RecordRepository;
import com.bootcamp.rule_engine.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    public final RecordRepository recordRepository;

    @Override
    public Record getRecord(UUID recordId) {
        Record record = recordRepository.findById(recordId).orElse(null);
        if(record != null){
            recordRepository.save(record);
        }
        return record;
    }

    @Override
    public Record createRecord(Record recordDTO) {
        return recordRepository.save(recordDTO);
    }

    @Override
    public List<Record> filterRecords(String field, String operation, String condition) {
        return recordRepository.filterRecords(field, operation, condition);
    }

    @Override
    public List<Record> getRecords() {
        return StreamSupport.stream(recordRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
}