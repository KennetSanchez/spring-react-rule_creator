package com.bootcamp.rule_engine.mapper;

import com.bootcamp.rule_engine.dto.RecordDTO;
import com.bootcamp.rule_engine.model.Record;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    Record fromDTO(RecordDTO recordDTO);
    RecordDTO fromRecord(Record record);
}
