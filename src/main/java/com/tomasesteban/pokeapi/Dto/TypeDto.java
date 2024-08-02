package com.tomasesteban.pokeapi.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class TypeDto {
    private Long id;
    private String name;
    private String url;

    private Map<String, String> damageRelations;
    private Map<String, String> pastDamageRelations;
    private Map<String, String> gameIndices;
    private Map<String, String> generation;
    private Map<String, String> moveDamageClass;
    private Map<String, String> names;
    private Map<String, String> pokemon;
    private Map<String, String> moves;
}
