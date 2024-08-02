package com.tomasesteban.pokeapi.Controller;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tomasesteban.pokeapi.Dto.TypeDto;

import com.tomasesteban.pokeapi.Models.Type;
import com.tomasesteban.pokeapi.Service.TypeService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("type")
@AllArgsConstructor
public class TypeController {

    @Autowired
    private ModelMapper modelMapper;
    private TypeService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable("id") Long id) throws IOException , InterruptedException {
        Type type = new Type();
		try {
			type = service.getTypeById(id);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        return ResponseEntity.ok().body(type);
    }
    
    @GetMapping(params = "name")
    public ResponseEntity<Type> getTypeByName(@RequestParam("name") String name) throws IOException , InterruptedException {

    	Type type = new Type();
		try {
			type = service.getTypeByName(name);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        return ResponseEntity.ok().body(type);
    }
}