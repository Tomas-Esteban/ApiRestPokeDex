package com.tomasesteban.pokeapi.Dto;


import lombok.Data;

import java.util.Map;

@Data
public class RegionDto {
    private Long id;
    private String name;
    private Map<String, String> locations;
    private Map<String, String> names;
    private Map<String, String> pokedexes;
    private Map<String, String> versionGroups;
    private Map<String, String> generation;
}
