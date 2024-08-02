package com.tomasesteban.pokeapi.Dto;

import lombok.Data;
import java.util.List;


@Data
public class StatsDto {
	private Long id;
    private String name;
    private int gameIndex;
    private boolean isBattleOnly;
    private String affectingMoves;
    private String affectingNatures;
    private List<String> characteristics;
    private String moveDamageClass;
    private List<NameDto> names;
}
