package com.tomasesteban.pokeapi.Service;

import java.io.IOException;
import java.util.List;


import com.tomasesteban.pokeapi.Models.Stats;

public interface StatService {

    Stats getStatsById(long id) throws IOException, InterruptedException;

	Stats getStatsByName(String name) throws IOException, InterruptedException;
}
