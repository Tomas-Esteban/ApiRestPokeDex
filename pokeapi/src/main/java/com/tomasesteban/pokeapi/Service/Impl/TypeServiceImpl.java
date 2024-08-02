package com.tomasesteban.pokeapi.Service.Impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.Repository.TypeRepository;
import com.tomasesteban.pokeapi.Service.TypeService;
import com.tomasesteban.pokeapi.Dto.TypeDto;
import com.tomasesteban.pokeapi.PokeApiInt.ApiResponse.TypeApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    private final TypeRepository repository;
    private final RestTemplate restTemplate;

    @Value("${pokeapi.base.url}")
    private String baseUrl;
    

    public TypeServiceImpl(TypeRepository repository, RestTemplate restTemplate) {
		super();
		this.repository = repository;
		this.restTemplate = restTemplate;
	}

	@Override
    public Type createType(Type type) throws Exception {
        try {
            log.info("Saving Type in database.");
            return repository.save(type);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Type> getAllTypes() throws Exception {
        try {
            log.info("Fetching all types from PokeAPI.");
            String url = baseUrl + "type?limit=1000"; 
            TypeApiResponse response = restTemplate.getForObject(url, TypeApiResponse.class);
            if (response == null || response.getResults().isEmpty()) {
                return Collections.emptyList();
            }
            return response.getResults().stream()
                    .map(this::mapToType)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error while fetching all types: " + e.getMessage());
        }
    }

    @Override
    public Type getTypeById(long id) throws NotFoundExcep {
        try {
            String url = baseUrl + "type/" + id;
            TypeDto typeDto = restTemplate.getForObject(url, TypeDto.class);
            if (typeDto == null) {
                throw new NotFoundExcep("Type with Id " + id + " was not found");
            }
            return mapToType(typeDto);
        } catch (Exception e) {
            throw new NotFoundExcep("Error while fetching type: " + e.getMessage());
        }
    }

    @Override
    public Type updateType(Type type) throws Exception {
        try {
            log.info("Updating Type with id " + type.getId());
            repository.save(type);
            return getTypeById(type.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteType(long id) throws NotFoundExcep {
        try {
            log.info("Deleting Type with id " + id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundExcep("Can't delete, Type with Id " + id + " does not exist");
        }
    }

    private Type mapToType(TypeDto typeDto) {
        // Convertir TypeDto a Type 
        Type type = new Type();
        type.setId(typeDto.getId());
        type.setName(typeDto.getName());
        return type;
    }
}