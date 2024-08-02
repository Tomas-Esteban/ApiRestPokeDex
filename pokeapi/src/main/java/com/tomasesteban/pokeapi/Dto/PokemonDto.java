package com.tomasesteban.pokeapi.Dto;
import lombok.Data;

import java.util.Map;

import com.tomasesteban.pokeapi.Models.Region;

@Data
	public class PokemonDto {
	
		    private Long id;
		    private String name;
		    private int height;
		    private int weight;
		    private Map<String,String> stats;
		    private Region region;
		    private Map<Integer, String> types;
	}