package com.tomasesteban.pokeapi.Service;

import java.util.List;

import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Stats;

public interface StatService {
    Stats createStats(Stats stats) throws Exception;

    List<Stats> getAllStats() throws Exception;

    Stats getStatsById(long id) throws NotFoundExcep;

    Stats updateStats(Stats stats) throws Exception;

    void deleteStats(long id) throws NotFoundExcep;
}
