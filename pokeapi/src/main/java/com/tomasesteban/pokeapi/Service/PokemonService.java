package com.tomasesteban.pokeapi.Service;

import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Models.Pokemon;

import java.io.IOException;
import java.util.List;



public interface PokemonService {

    List<Pokemon> getAllPokemon() throws Exception;
    Pokemon getPokemonById(long id) throws IOException, InterruptedException;
    Pokemon getPokemonByName(String name) throws  IOException, InterruptedException;

}
