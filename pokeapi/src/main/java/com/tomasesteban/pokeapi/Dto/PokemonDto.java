package com.tomasesteban.pokeapi.Dto;
import lombok.Data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.tomasesteban.pokeapi.Models.Generation;
import com.tomasesteban.pokeapi.Models.Region;
import com.tomasesteban.pokeapi.Models.Stats;
import com.tomasesteban.pokeapi.Models.Type;

@Data
	public class PokemonDto {
	
		    private Long id;
		    private String name;
		    private int height;
		    private int weight;
		    private Generation generation;
		    private Stats stats;
		    private Region region;
		    private List<Type> types;
	}