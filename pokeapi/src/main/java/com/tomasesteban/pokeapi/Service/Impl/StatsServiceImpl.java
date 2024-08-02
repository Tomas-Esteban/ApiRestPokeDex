package com.tomasesteban.pokeapi.Service.Impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Stats;
import com.tomasesteban.pokeapi.Repository.StatsRepository;
import com.tomasesteban.pokeapi.Service.StatService;
import com.tomasesteban.pokeapi.Dto.StatsDto;
import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.StatsApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatsServiceImpl implements StatService {

    private final StatsRepository repository;
    private final RestTemplate restTemplate;

    @Value("${pokeapi.base.url}")
    private String baseUrl;

    public StatsServiceImpl(StatsRepository repository, RestTemplate restTemplate) {
		super();
		this.repository = repository;
		this.restTemplate = restTemplate;
	}
    @Override
    public Stats createStats(Stats stats) throws Exception {
        try {
            log.info("Saving Stats in database.");
            return repository.save(stats);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Stats> getAllStats() throws Exception {
        try {
            log.info("Fetching all stats from PokeAPI.");
            String url = baseUrl + "stat?limit=1000"; 
            StatsApiResponse response = restTemplate.getForObject(url, StatsApiResponse.class);
            if (response == null || response.getResults().isEmpty()) {
                return Collections.emptyList();
            }
            return response.getResults().stream()
                    .map(this::mapToStats)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error while fetching all stats: " + e.getMessage());
        }
    }

    @Override
    public Stats getStatsById(long id) throws NotFoundExcep {
        try {
            String url = baseUrl + "stat/" + id;
            StatsDto statsDto = restTemplate.getForObject(url, StatsDto.class);
            if (statsDto == null) {
                throw new NotFoundExcep("Stats with Id " + id + " was not found");
            }
            return mapToStats(statsDto);
        } catch (Exception e) {
            throw new NotFoundExcep("Error while fetching stats: " + e.getMessage());
        }
    }

    @Override
    public Stats updateStats(Stats stats) throws Exception {
        try {
            log.info("Updating Stats with id " + stats.getId());
            repository.save(stats);
            return getStatsById(stats.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteStats(long id) throws NotFoundExcep {
        try {
            log.info("Deleting Stats with id " + id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundExcep("Can't delete, Stats with Id " + id + " does not exist");
        }
    }

    private Stats mapToStats(StatsDto statsDto) {
        // Convertir StatsDto a Stats
        Stats stats = new Stats();
        stats.setId(statsDto.getId());
        
        return stats;
    }

	
}
