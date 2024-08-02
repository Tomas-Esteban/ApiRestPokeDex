package com.tomasesteban.pokeapi.PokeApiInt.ApiResponse;

import java.util.List;
import com.tomasesteban.pokeapi.Dto.TypeDto;

public class TypeApiResponse {
    private List<TypeDto> results;

    // Getters y setters

    public List<TypeDto> getResults() {
        return results;
    }

    public void setResults(List<TypeDto> results) {
        this.results = results;
    }
}
