package com.tomasesteban.pokeapi.PokeApiInt.ApiResponse;

import java.util.List;

import com.tomasesteban.pokeapi.Dto.PokemonDto;

public class PokemonApiResponse {
    private List<PokemonDto> results;

    // Getters y setters

    public List<PokemonDto> getResults() {
        return results;
    }

    public void setResults(List<PokemonDto> results) {
        this.results = results;
    }
}