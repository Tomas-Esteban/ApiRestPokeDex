package com.tomasesteban.pokeapi.Service;

import com.tomasesteban.pokeapi.Dto.PokemonDto;
import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Pokemon;


import java.util.List;



public interface PokemonService {

    List<PokemonDto> getAllPokemon() throws Exception;
    PokemonDto getPokemonById(long id) throws NotFoundExcep;
    PokemonDto getPokemonByName(String name) throws NotFoundExcep;

}
