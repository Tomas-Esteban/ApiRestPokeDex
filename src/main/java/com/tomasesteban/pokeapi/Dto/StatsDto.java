package com.tomasesteban.pokeapi.Dto;

import lombok.Data;
import java.util.List;
import java.util.Map;


@Data
public class StatsDto {
	    private Long id;
	    private String name;
	    private int gameIndex;
	    private boolean isBattleOnly;

	    private Map<String,String> affectingMoves; 


	    private Map<String,String> affectingNatures;
	    
	    private List<String> characteristics;

	    private Map<String, String> moveDamageClass;

	    private Map<String,String> names;
}
