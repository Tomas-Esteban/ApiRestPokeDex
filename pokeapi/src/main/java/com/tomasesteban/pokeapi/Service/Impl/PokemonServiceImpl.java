package com.tomasesteban.pokeapi.Service.Impl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Models.Pokemon;
import com.tomasesteban.pokeapi.Models.Stats;
import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.PokemonApiResponse;
import com.tomasesteban.pokeapi.Service.PokemonService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    
    @Value("${pokeapi.base.url}")
    private String baseUrl;

    public PokemonServiceImpl(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public List<Pokemon> getAllPokemon() throws Exception {
		String url = baseUrl + "pokemon/";
		HttpResponse<String> response = null;
		Pokemon p = new Pokemon();
        PokemonDto pDto = new PokemonDto();
        List<Pokemon> pList = new ArrayList();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			pDto = parseJsonToPokemon(response.body())	;
			p = mapToPokemon(pDto);
			pList.add(p);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return pList;
    }

    public Pokemon getPokemonById(long id) throws IOException , InterruptedException {
        String url = baseUrl + "pokemon/" + id;
        HttpResponse<String> response = null;
        Pokemon p = new Pokemon();
        PokemonDto pDto = new PokemonDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			pDto = parseJsonToPokemon(response.body())	;
			p = mapToPokemon(pDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return p;
        
        
    }

    public Pokemon getPokemonByName(String name) throws IOException ,InterruptedException{
        String url = baseUrl + "pokemon/" + name;
        HttpResponse<String> response = null;
        Pokemon p = new Pokemon();
        PokemonDto pDto = new PokemonDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			pDto = parseJsonToPokemon(response.body())	;
			p = mapToPokemon(pDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return p;
    }

    private Pokemon mapToPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setHeight(pokemonDto.getHeight());
        pokemon.setWeight(pokemonDto.getWeight());
        pokemon.setStats(pokemonDto.getStats());
        pokemon.setTypes(pokemonDto.getTypes());
        return pokemon;
    }
    
    private PokemonDto parseJsonToPokemon(String json) {
        PokemonDto pokemon = new PokemonDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            pokemon.setId(rootNode.path("id").asLong());
            pokemon.setName(rootNode.path("name").asText());
            pokemon.setHeight(rootNode.path("height").asInt());
            pokemon.setWeight(rootNode.path("weight").asInt());

            pokemon.setStats(parseStatsMap(rootNode.path("stats")));

            pokemon.setTypes(parseTypesMap(rootNode.path("types")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    private Map<String, String> parseStatsMap(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                String statName = item.path("stat").path("name").asText();
                String statValue = Integer.toString(item.path("base_stat").asInt());
                map.put(statName, statValue);
            }
        }
        return map;
    }

    private Map<Integer, String> parseTypesMap(JsonNode node) {
        Map<Integer, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                int typeSlot = item.path("slot").asInt();
                String typeName = item.path("type").path("name").asText();
                String typeUrl = item.path("type").path("url").asText();
                map.put(typeSlot, typeName + " - " + typeUrl);
            }
        }
        return map;
    }
}