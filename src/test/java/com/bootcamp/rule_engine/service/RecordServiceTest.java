package com.bootcamp.rule_engine.service;

import com.bootcamp.rule_engine.repository.RecordRepository;
import com.bootcamp.rule_engine.service.impl.RecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bootcamp.rule_engine.model.Record;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecordServiceTest {

    private RecordService recordService;
    private RecordRepository recordRepository;
    private UUID recordId;
    private Record record;

    @BeforeEach
    void setUp() {
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordServiceImpl(recordRepository);
        recordId = UUID.randomUUID();
        record = new Record(recordId);
    }

    @Test
    void getRecord() {
        when(recordService.getRecord(recordId)).thenReturn(record);
        assertNotNull(recordService.getRecord(recordId));
    }

    @Test
    void createRecord() {
    }

    @Test
    void getRecords() {
    }
}
