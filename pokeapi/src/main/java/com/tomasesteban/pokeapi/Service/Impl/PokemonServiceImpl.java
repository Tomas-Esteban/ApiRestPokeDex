package com.tomasesteban.pokeapi.Service.Impl;
import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Models.Pokemon;

import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.PokemonApiResponse;
import com.tomasesteban.pokeapi.Service.PokemonService;
import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.CollectionUtils;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final RestTemplate restTemplate;

    @Value("${pokeapi.base.url}")
    private String baseUrl;

    public PokemonServiceImpl(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public List<PokemonDto> getAllPokemon() throws Exception {
        try {
            log.info("Fetching all Pokemon from PokeAPI.");
            String url = baseUrl + "pokemon?limit=10";  
            PokemonApiResponse response = (PokemonApiResponse) restTemplate.getForObject(url, PokemonApiResponse.class);
            if (response == null || CollectionUtils.isEmpty(response.getResults())) {
                return Collections.emptyList();
            }
            return response.getResults().stream()
                    .map(pokemonDto -> pokemonDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error while fetching all pokemon." + e.getMessage());
        }
    }

    public PokemonDto getPokemonById(long id) throws NotFoundExcep {
        String url = baseUrl + "pokemon/" + id;
        return restTemplate.getForObject(url, PokemonDto.class);
        /*PokemonDto pokemonDto = restTemplate.getForObject(url, PokemonDto.class);
        if (pokemonDto == null) {
            throw new NotFoundExcep("Pokemon with Id " + id + " was not found");
        }
        return mapToPokemon(pokemonDto);*/
    }

    public PokemonDto getPokemonByName(String name) throws NotFoundExcep {
        String url = baseUrl + "pokemon/" + name;
        return restTemplate.getForObject(url, PokemonDto.class);
        /*PokemonDto pokemonDto = restTemplate.getForObject(url, PokemonDto.class);
        if (pokemonDto == null) {
            throw new NotFoundExcep("Pokemon with name " + name + " was not found");
        }
        return mapToPokemon(pokemonDto);*/
    }

    private Pokemon mapToPokemon(PokemonDto pokemonDto) {
        // Convertir PokemonDto a Pokemon 
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setGeneration(pokemonDto.getGeneration());
        pokemon.setHeight(pokemonDto.getHeight());
        pokemon.setName(pokemonDto.getName());
        pokemon.setRegion(pokemonDto.getRegion());
        pokemon.setStats(pokemonDto.getStats());
        pokemon.setTypes(pokemonDto.getTypes());
        pokemon.setWeight(pokemonDto.getWeight());
        return pokemon;
    }
}