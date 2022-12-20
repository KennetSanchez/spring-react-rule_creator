package com.bootcamp.rule_engine.api;

import com.bootcamp.rule_engine.dto.RecordDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping("/records")
public interface RecordsAPI {

    @GetMapping("/{recordId}")
    RecordDTO getRecord(@PathVariable UUID recordId);

    @PostMapping()
    RecordDTO createRecord(@RequestBody @Valid RecordDTO recordDTO);

    @GetMapping
    List<RecordDTO> getRecords();
}
