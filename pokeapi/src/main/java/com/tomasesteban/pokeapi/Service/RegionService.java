package com.tomasesteban.pokeapi.Service;

import java.io.IOException;
import java.util.List;


import com.tomasesteban.pokeapi.Models.Region;

public interface RegionService {

    Region getRegionById(long id) throws IOException , InterruptedException;

	Region getRegionByName(String name) throws  IOException, InterruptedException;
}