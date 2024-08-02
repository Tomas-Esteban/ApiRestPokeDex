	package com.tomasesteban.pokeapi.Controller;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomasesteban.pokeapi.Dto.StatsDto;
import com.tomasesteban.pokeapi.Exception.NotFoundExcep;
import com.tomasesteban.pokeapi.Models.Stats;
import com.tomasesteban.pokeapi.Service.StatService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stats")
@AllArgsConstructor
public class StatsController {

    @Autowired
    private ModelMapper modelMapper;
    private StatService service;

    @GetMapping
    public ResponseEntity<List<StatsDto>> getAllStats() throws Exception {

        List<StatsDto> statsResponse = service.getAllStats()
                .stream()
                .map(stats -> modelMapper.map(stats, StatsDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(statsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDto> getStatsByStats(@PathVariable("id") long id) throws NotFoundExcep {

        Stats stats = service.getStatsById(id);
        StatsDto statsResponse = modelMapper.map(stats, StatsDto.class);
        return ResponseEntity.ok().body(statsResponse);
    }

    @PostMapping
    public ResponseEntity<StatsDto> createStats(@RequestBody StatsDto StatsDto) throws Exception {

        Stats statsRequest = modelMapper.map(StatsDto, Stats.class);
        Stats stats = service.createStats(statsRequest);
        StatsDto statsResponse = modelMapper.map(stats, StatsDto.class);

        return new ResponseEntity<StatsDto>(statsResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StatsDto> updateStats(@RequestBody StatsDto StatsDto) throws Exception {

        Stats statsRequest = modelMapper.map(StatsDto, Stats.class);
        Stats stats = service.updateStats(statsRequest);
        StatsDto statsResponse = modelMapper.map(stats, StatsDto.class);
        return ResponseEntity.ok().body(statsResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStats(@PathVariable("id") long id) throws Exception {

        service.deleteStats(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}