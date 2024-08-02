package com.tomasesteban.pokeapi.Service.Impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Region;
import com.tomasesteban.pokeapi.Repository.RegionRepository;
import com.tomasesteban.pokeapi.Service.RegionService;
import com.tomasesteban.pokeapi.Dto.RegionDto;
import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.RegionApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;
    private final RestTemplate restTemplate;

    @Value("${pokeapi.base.url}")
    private String baseUrl;

    public RegionServiceImpl(RegionRepository repository, RestTemplate restTemplate) {
		super();
		this.repository = repository;
		this.restTemplate = restTemplate;
	}

	@Override
    public Region createRegion(Region region) throws Exception {
        try {
            log.info("Saving Region in DB.");
            return repository.save(region);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Region> getAllRegions() throws Exception {
        try {
            log.info("Fetching all regions from PokeAPI.");
            String url = baseUrl + "region?limit=1000";
            RegionApiResponse response = restTemplate.getForObject(url, RegionApiResponse.class);
            if (response == null || response.getResults().isEmpty()) {
                return Collections.emptyList();
            }
            return response.getResults().stream()
                    .map(this::mapToRegion)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error while fetching all regions: " + e.getMessage());
        }
    }

    @Override
    public Region getRegionById(long id) throws NotFoundExcep {
        try {
            String url = baseUrl + "region/" + id;
            RegionDto regionDto = restTemplate.getForObject(url, RegionDto.class);
            if (regionDto == null) {
                throw new NotFoundExcep("Region with Id " + id + " was not found");
            }
            return mapToRegion(regionDto);
        } catch (Exception e) {
            throw new NotFoundExcep("Error while fetching region: " + e.getMessage());
        }
    }

    @Override
    public Region updateRegion(Region region) throws Exception {
        try {
            log.info("Updating Region with id " + region.getId());
            repository.save(region);
            return getRegionById(region.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteRegion(long id) throws NotFoundExcep {
        try {
            log.info("Deleting Region with id " + id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundExcep("Can't delete, Region with Id " + id + " does not exist");
        }
    }

    private Region mapToRegion(RegionDto regionDto) {
        // Convertir RegionDto a Region 
        Region region = new Region();
        region.setId(regionDto.getId());
        region.setName(regionDto.getName());
        return region;
    }
}