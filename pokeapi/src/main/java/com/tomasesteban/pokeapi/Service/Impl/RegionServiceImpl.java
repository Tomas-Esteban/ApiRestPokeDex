package com.tomasesteban.pokeapi.Service.Impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import com.tomasesteban.pokeapi.Models.Region;
import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.Repository.RegionRepository;
import com.tomasesteban.pokeapi.Service.RegionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Dto.RegionDto;
import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.RegionApiResponse;

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
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Value("${pokeapi.base.url}")
    private String baseUrl;

    public RegionServiceImpl(RegionRepository repository, RestTemplate restTemplate) {
		super();
		this.repository = repository;
		this.restTemplate = restTemplate;
	}

    @Override
    public Region getRegionById(long id) throws IOException , InterruptedException {
    	String url = baseUrl + "region/" + id;
        HttpResponse<String> response = null;
        Region r = new Region();
        RegionDto rDto = new RegionDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			rDto = parseJsonToRegion(response.body());
			r = mapToRegion(rDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return r;
    }

    @Override
    public Region getRegionByName(String name) throws IOException , InterruptedException {
    	String url = baseUrl + "region/?name=" + name;
        HttpResponse<String> response = null;
        Region r = new Region();
        RegionDto rDto = new RegionDto();
        try {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			rDto = parseJsonToRegion(response.body());
			r = mapToRegion(rDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return r;
    }
    private Region mapToRegion(RegionDto regionDto) {
        Region region = new Region();
        region.setId(regionDto.getId());
        region.setName(regionDto.getName());
        region.setLocations(regionDto.getLocations());
        region.setNames(regionDto.getNames());
        region.setPokedexes(regionDto.getPokedexes());
        region.setVersionGroups(regionDto.getVersionGroups());
        region.setGeneration(regionDto.getGeneration());
        return region;
    }
    
    private RegionDto parseJsonToRegion(String json) {
        RegionDto region = new RegionDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            region.setId(rootNode.path("id").asLong());
            region.setName(rootNode.path("name").asText());

            region.setLocations(parseSimpleMap(rootNode.path("locations")));
            region.setNames(parseLanguageMap(rootNode.path("names")));
            region.setPokedexes(parseSimpleMap(rootNode.path("pokedexes")));
            region.setVersionGroups(parseSimpleMap(rootNode.path("version_groups")));
            region.setGeneration(parseSimpleMap(rootNode.path("generation")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
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

    private Map<String, String> parseLanguageMap(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        if (node.isArray()) {
            for (JsonNode item : node) {
                String nameValue = item.path("name").asText();
                JsonNode languageNode = item.path("language");
                String languageName = languageNode.path("name").asText();
                map.put(languageName, nameValue);
            }
        }
        return map;
    }
}