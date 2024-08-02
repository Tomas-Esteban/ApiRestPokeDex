package com.tomasesteban.pokeapi.Dto;
import lombok.Data;

import java.util.List;
import java.util.Map;

import com.tomasesteban.pokeapi.Models.Region;

@Data
	public class PokemonDto {
	
		    private Long id;
		    private String name;
		    private int height;
		    private int weight;
		    private List<String> abilities; 
		    private List<String> moves; 
		    private Map<String, String> stats; 
		    private Map<Integer, String> types; 
		    private String pictureUrl;
		    private String description; 
	}