package com.bootcamp.rule_engine.controller;

import com.bootcamp.rule_engine.api.RecordAPI;
import com.bootcamp.rule_engine.dto.RecordDTO;
import com.bootcamp.rule_engine.mapper.RecordMapper;
import com.bootcamp.rule_engine.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class RecordController implements RecordAPI {

    public final RecordService recordService;
    public final RecordMapper recordMapper;

    @Override
    public RecordDTO getRecord(UUID recordId) {
        return recordMapper.fromRecord(recordService.getRecord(recordId));
    }

    @Override
    public RecordDTO createRecord(RecordDTO recordDTO) {
        return recordMapper.fromRecord(recordService.createRecord(recordMapper.fromDTO(recordDTO)));
    }

    @Override
    public List<RecordDTO> getRecords() {
        return recordService.getRecords().stream().map(recordMapper::fromRecord).collect(Collectors.toList());
    }

    @Override
    public List<RecordDTO> filterRecords(String finalQuery) {
        return recordService.filterRecords(finalQuery).stream().map(recordMapper::fromRecord).collect(Collectors.toList());
    }
}
