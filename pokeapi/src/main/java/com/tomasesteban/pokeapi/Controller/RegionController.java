package com.tomasesteban.pokeapi.Controller;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomasesteban.pokeapi.Dto.RegionDto;
import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Region;
import com.tomasesteban.pokeapi.Service.RegionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("region")
@AllArgsConstructor
public class RegionController {

    @Autowired
    private ModelMapper modelMapper;
    private RegionService service;

    @GetMapping
    private ResponseEntity<List<RegionDto>> findAll() throws Exception {
        List<RegionDto> regionResponse = service.getAllRegions()
                .stream()
                .map(region -> modelMapper.map(region, RegionDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(regionResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegionDto> findById(@PathVariable("id") Long id) throws NotFoundExcep {
        Region region = service.getRegionById(id);
        RegionDto regionResponse = modelMapper.map(region, RegionDto.class);
        return ResponseEntity.ok().body(regionResponse);
    }

    @PostMapping
    public ResponseEntity<RegionDto> create(@RequestBody RegionDto RegionDto) throws Exception {
        Region regionRequest = modelMapper.map(RegionDto, Region.class);
        Region region = service.createRegion(regionRequest);
        RegionDto regionResponse = modelMapper.map(region, RegionDto.class);

        return new ResponseEntity<RegionDto>(regionResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RegionDto> update(@RequestBody RegionDto RegionDto) throws Exception {
        Region regionRequest = modelMapper.map(RegionDto, Region.class);
        Region region = service.updateRegion(regionRequest);
        RegionDto regionResponse = modelMapper.map(region, RegionDto.class);
        return ResponseEntity.ok().body(regionResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {

        service.deleteRegion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}