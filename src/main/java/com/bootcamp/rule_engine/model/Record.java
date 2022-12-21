package com.bootcamp.rule_engine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Table(name = "RECORDS")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    private UUID recordId;

}
