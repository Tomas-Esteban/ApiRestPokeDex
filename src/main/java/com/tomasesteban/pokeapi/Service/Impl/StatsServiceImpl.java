package com.tomasesteban.pokeapi.Service.Impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.tomasesteban.pokeapi.Models.Pokemon;
import com.tomasesteban.pokeapi.Models.Stats;
import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.Repository.StatsRepository;
import com.tomasesteban.pokeapi.Service.StatService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasesteban.pokeapi.Dto.NameDto;
import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Dto.StatsDto;
import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.StatsApiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatsServiceImpl implements StatService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Value("${pokeapi.base.url}")
    private String baseUrl;

    public StatsServiceImpl( RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

    @Override
    public Stats getStatsById(long id) throws IOException, InterruptedException {
    	String url = baseUrl + "stat/" + id;
		HttpResponse<String> response = null;
		Stats s = new Stats();
		StatsDto sDto = new StatsDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			sDto = parseJsonToStat(response.body());
			s = mapToStat(sDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return s;
    }
    
    @Override
    public Stats getStatsByName(String name) throws IOException , InterruptedException {
    	String url = baseUrl + "stat/" + name;
		HttpResponse<String> response = null;
		Stats s = new Stats();
		StatsDto sDto = new StatsDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			sDto = parseJsonToStat(response.body());
			s = mapToStat(sDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return s;
    }


    private Stats mapToStat(StatsDto statsDto) {
    	Stats stats = new Stats();
    	stats.setId(statsDto.getId());
        stats.setName(statsDto.getName());
        stats.setGameIndex(statsDto.getGameIndex());
        stats.setBattleOnly(statsDto.isBattleOnly());
        stats.setAffectingMoves(statsDto.getAffectingMoves());
        stats.setAffectingNatures(statsDto.getAffectingNatures());
        stats.setCharacteristics(statsDto.getCharacteristics());
        stats.setMoveDamageClass(statsDto.getMoveDamageClass());
        stats.setNames(statsDto.getNames());
        return stats;
    }
    
    private StatsDto parseJsonToStat(String json) {
        StatsDto stats = new StatsDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            stats.setId(rootNode.path("id").asLong());
            stats.setName(rootNode.path("name").asText());
            stats.setGameIndex(rootNode.path("gameIndex").asInt());
            stats.setBattleOnly(rootNode.path("battleOnly").asBoolean());
            stats.setAffectingMoves(parseSimpleMap(rootNode.path("affectingMoves")));
            stats.setAffectingNatures(parseSimpleMap(rootNode.path("affectingNatures")));
            JsonNode characteristicsNode = rootNode.path("characteristics");
            List<String> characteristics = new ArrayList<>();
            if (characteristicsNode.isArray()) {
                for (JsonNode item : characteristicsNode) {
                    characteristics.add(item.asText());
                }
            }
            stats.setCharacteristics(characteristics);
            stats.setMoveDamageClass(parseSimpleMap(rootNode.path("moveDamageClass")));
            stats.setNames(parseSimpleMap(rootNode.path("names")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }

    private Map<String, String> parseSimpleMap(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                map.put(item.path("name").asText(), item.path("url").asText());
            }
        } else if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                map.put(entry.getKey(), entry.getValue().asText());
            });
        }
        return map;
    }

	
}
