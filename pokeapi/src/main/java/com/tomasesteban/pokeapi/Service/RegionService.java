package com.tomasesteban.pokeapi.Service;

import java.util.List;

import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Region;

public interface RegionService {
    List<Region> getAllRegions() throws Exception;

    Region getRegionById(long id) throws NotFoundExcep;

    Region createRegion(Region region) throws Exception;

    Region updateRegion(Region region) throws Exception;

    void deleteRegion(long id) throws NotFoundExcep	;
}