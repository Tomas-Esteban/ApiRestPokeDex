package com.tomasesteban.pokeapi.Controller;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomasesteban.pokeapi.Dto.RegionDto;

import com.tomasesteban.pokeapi.Models.Region;
import com.tomasesteban.pokeapi.Service.RegionService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/region")
@AllArgsConstructor
public class RegionController {

    @Autowired
    private ModelMapper modelMapper;
    private RegionService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegionDto> getRegionById(@PathVariable("id") Long id) throws IOException , InterruptedException {
        Region region = new Region();
		try {
			region = service.getRegionById(id);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        RegionDto regionResponse = modelMapper.map(region, RegionDto.class);
        return ResponseEntity.ok().body(regionResponse);
    }
    
    @GetMapping(params = "name")
    public ResponseEntity<RegionDto> getRegionByName(@RequestParam("name") String name) throws IOException , InterruptedException {
    	Region region = new Region();
		try {
			region = service.getRegionByName(name);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        RegionDto regionResponse = modelMapper.map(region, RegionDto.class);
        return ResponseEntity.ok().body(regionResponse);
    }

}