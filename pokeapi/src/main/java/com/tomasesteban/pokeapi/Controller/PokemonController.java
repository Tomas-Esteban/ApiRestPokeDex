package com.tomasesteban.pokeapi.Controller;
import com.tomasesteban.pokeapi.Dto.PokemonDto;

import com.tomasesteban.pokeapi.Models.Pokemon;
import com.tomasesteban.pokeapi.Service.PokemonService;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PokemonService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") Long id) throws IOException , InterruptedException {
    	Pokemon pokemon = service.getPokemonById(id);
        return ResponseEntity.ok().body(pokemon);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Pokemon> getPokemonByName(@RequestParam("name") String name) throws IOException , InterruptedException {
        Pokemon pokemon = new Pokemon();
		try {
			pokemon = service.getPokemonByName(name);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        return ResponseEntity.ok().body(pokemon);
    }

} 
