package com.bootcamp.rule_engine.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecretProvider {
    secretKey("Perficient_bootcamp-2022/20-12:team1.Ices! | we need to make this String longer so the hash become secure" +
            " enough, we hope this one is long enough. :)");
    private final String secret;
}