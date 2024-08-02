package com.tomasesteban.pokeapi.PokeApiInt.ApiResponse;

import java.util.List;
import com.tomasesteban.pokeapi.Dto.RegionDto;

public class RegionApiResponse {
    private List<RegionDto> results;

    // Getters y setters

    public List<RegionDto> getResults() {
        return results;
    }

    public void setResults(List<RegionDto> results) {
        this.results = results;
    }
}