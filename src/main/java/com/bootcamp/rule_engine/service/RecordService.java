package com.bootcamp.rule_engine.service;

import com.bootcamp.rule_engine.model.Record;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface RecordService {

    Record getRecord(@PathVariable UUID recordId);

    Record createRecord(@RequestBody Record recordDTO);

    List<Record> filterRecords(@RequestParam String queryString);

    List<Record> getRecords();
}
