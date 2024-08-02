package com.tomasesteban.pokeapi.Service;

import java.io.IOException;
import java.util.List;


import com.tomasesteban.pokeapi.Models.Generation;

public interface GenerationService {
    Generation createGeneration(Generation generation) throws Exception;

    List<Generation> getAllGenerations() throws Exception;

    Generation getGenerationById(long id) throws IOException, InterruptedException;

    Generation updateGeneration(Generation generation) throws Exception;

    void deleteGeneration(long id) throws IOException, InterruptedException;
}
