package com.tomasesteban.pokeapi.PokeApiInt.ApiResponse;

import java.util.List;
import com.tomasesteban.pokeapi.Dto.StatsDto;

public class StatsApiResponse {
    private List<StatsDto> results;

    // Getters y setters

    public List<StatsDto> getResults() {
        return results;
    }

    public void setResults(List<StatsDto> results) {
        this.results = results;
    }
}
