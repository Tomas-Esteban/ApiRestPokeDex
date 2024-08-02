package com.tomasesteban.pokeapi.Service.Impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.tomasesteban.pokeapi.Models.Pokemon;
import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.Repository.TypeRepository;
import com.tomasesteban.pokeapi.Service.TypeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Dto.TypeDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    

    @Value("${pokeapi.base.url}")
    private String baseUrl;
    

    public TypeServiceImpl(TypeRepository repository, RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}


    @Override
    public Type getTypeById(long id) throws IOException , InterruptedException  {
    	 String url = baseUrl + "type/" + id;
         HttpResponse<String> response = null;
         Type t = new Type();
         TypeDto tDto = new TypeDto();
         try {
         	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
 			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
 			tDto = parseJsonToType(response.body())	;
 			t = mapToType(tDto);
 		} catch (IOException e) {
 			e.printStackTrace();
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
         return t;
    }

    public Type getTypeByName(String name) throws IOException ,InterruptedException{
        String url = baseUrl + "type/" + name;
        HttpResponse<String> response = null;
        Type p = new Type();
        TypeDto pDto = new TypeDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			pDto = parseJsonToType(response.body())	;
			p = mapToType(pDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return p;
    }
    
    private Type mapToType(TypeDto typeDto) {
    	Type t = new Type();
        t.setId(typeDto.getId());
        t.setName(typeDto.getName());
        t.setUrl(typeDto.getUrl());
        t.setDamageRelations(typeDto.getDamageRelations());
        t.setPastDamageRelations(typeDto.getPastDamageRelations());
        t.setGameIndices(typeDto.getGameIndices());
        t.setGeneration(typeDto.getGeneration());
        t.setMoveDamageClass(typeDto.getMoveDamageClass());
        t.setNames(typeDto.getNames());
        t.setPokemon(typeDto.getPokemon());
        t.setMoves(typeDto.getMoves());

        return t;
    }
    
    private TypeDto parseJsonToType(String json) {
        TypeDto type = new TypeDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            type.setId(rootNode.path("id").asLong());
            type.setName(rootNode.path("name").asText());
            type.setUrl(rootNode.path("generation").path("url").asText());

            type.setDamageRelations(parseSimpleMap(rootNode.path("damage_relations")));
            type.setPastDamageRelations(parsePastDamageRelations(rootNode.path("past_damage_relations")));
            type.setGameIndices(parseGameIndices(rootNode.path("game_indices")));
            type.setGeneration(parseSimpleMap(rootNode.path("generation")));
            type.setMoveDamageClass(parseSimpleMap(rootNode.path("move_damage_class")));
            type.setNames(parseNames(rootNode.path("names")));
            type.setPokemon(parseSimpleMapArray(rootNode.path("pokemon"), "pokemon"));
            type.setMoves(parseSimpleMapArray(rootNode.path("moves"), "name"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    private Map<String, String> parseSimpleMap(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                map.put(entry.getKey(), entry.getValue().asText());
            });
        }
        return map;
    }

    private Map<String, String> parsePastDamageRelations(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                String generation = item.path("generation").path("name").asText();
                map.put(generation, item.path("damage_relations").toString());
            }
        }
        return map;
    }

    private Map<String, String> parseGameIndices(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                String gameIndex = item.path("game_index").asText();
                String generation = item.path("generation").path("name").asText();
                map.put(gameIndex, generation);
            }
        }
        return map;
    }

    private Map<String, String> parseNames(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                String language = item.path("language").path("name").asText();
                String name = item.path("name").asText();
                map.put(language, name);
            }
        }
        return map;
    }

    private Map<String, String> parseSimpleMapArray(JsonNode node, String keyName) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                String key = item.path(keyName).path("name").asText();
                String value = item.path(keyName).path("url").asText();
                map.put(key, value);
            }
        }
        return map;
    }



}