package com.bootcamp.rule_engine.dto;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {

    private UUID recordId;

    @PrePersist
    private void setId(){this.recordId = UUID.randomUUID();}
}

