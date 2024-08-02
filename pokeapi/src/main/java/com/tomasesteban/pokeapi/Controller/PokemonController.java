package com.tomasesteban.pokeapi.Controller;
import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Pokemon;
import com.tomasesteban.pokeapi.Service.PokemonService;
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
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable("id") Long id) throws NotFoundExcep {
    	PokemonDto pokemon = service.getPokemonById(id);
        return ResponseEntity.ok().body(pokemon);
    }

    @GetMapping(params = "name")
    public ResponseEntity<PokemonDto> getPokemonByName(@RequestParam("name") String name) throws NotFoundExcep {
        PokemonDto pokemon = service.getPokemonByName(name);
        return ResponseEntity.ok().body(pokemon);
    }

} 
