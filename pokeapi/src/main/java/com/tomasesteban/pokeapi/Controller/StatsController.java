	package com.tomasesteban.pokeapi.Controller;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomasesteban.pokeapi.Dto.StatsDto;

import com.tomasesteban.pokeapi.Models.Stats;
import com.tomasesteban.pokeapi.Service.StatService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stat")
@AllArgsConstructor
public class StatsController {

    @Autowired
    private ModelMapper modelMapper;
    private StatService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<StatsDto> getStatsById(@PathVariable("id") long id) throws IOException, InterruptedException {
        Stats stats = new Stats();
		try {
			stats = service.getStatsById(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StatsDto statsResponse = modelMapper.map(stats, StatsDto.class);
        return ResponseEntity.ok().body(statsResponse);
    }
    
    @GetMapping(params = "name")
    public ResponseEntity<StatsDto> getStatsByName(@RequestParam("name") String name) throws IOException, InterruptedException {
    	Stats stats = new Stats();
		try {
			stats = service.getStatsByName(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StatsDto statsResponse = modelMapper.map(stats, StatsDto.class);
        return ResponseEntity.ok().body(statsResponse);
    }

}