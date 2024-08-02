package com.tomasesteban.pokeapi.PokeApiInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "https://pokeapi.co/api/v2/";

    public String getPokemonByName(String name) {
        String url = baseUrl + "pokemon/" + name;
        return restTemplate.getForObject(url, String.class);
    }
}
